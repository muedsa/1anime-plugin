package com.muedsa.tvbox.an1me.service

import com.muedsa.tvbox.an1me.An1meConst
import com.muedsa.tvbox.an1me.model.PlayerAAAA
import com.muedsa.tvbox.api.data.MediaCard
import com.muedsa.tvbox.api.data.MediaCardRow
import com.muedsa.tvbox.api.data.MediaDetail
import com.muedsa.tvbox.api.data.MediaEpisode
import com.muedsa.tvbox.api.data.MediaHttpSource
import com.muedsa.tvbox.api.data.MediaPlaySource
import com.muedsa.tvbox.api.data.SavedMediaCard
import com.muedsa.tvbox.api.service.IMediaDetailService
import com.muedsa.tvbox.tool.ChromeUserAgent
import com.muedsa.tvbox.tool.LenientJson
import com.muedsa.tvbox.tool.checkSuccess
import com.muedsa.tvbox.tool.decodeBase64ToStr
import com.muedsa.tvbox.tool.feignChrome
import com.muedsa.tvbox.tool.get
import com.muedsa.tvbox.tool.parseHtml
import com.muedsa.tvbox.tool.toRequestBuild
import okhttp3.OkHttpClient
import org.jsoup.nodes.Element
import timber.log.Timber

class MediaDetailService(
    private val an1meService: An1meService,
    private val okHttpClient: OkHttpClient
) : IMediaDetailService {

    override suspend fun getDetailData(mediaId: String, detailUrl: String): MediaDetail {
        if (!detailUrl.startsWith("/voddetail/")) {
            throw RuntimeException("不支持的类型 $detailUrl")
        }
        val body = "${an1meService.getSiteUrl()}$detailUrl".toRequestBuild()
            .feignChrome()
            .get(okHttpClient = okHttpClient)
            .checkSuccess()
            .parseHtml()
            .body()
        val boxEl = body.selectFirst("#main .content .box")!!
        val imgUrl = boxEl.selectFirst(".module-item-cover .module-item-pic img")!!
            .attr("data-src")
        val title = boxEl.selectFirst(".video-info .video-info-header .page-title")!!
            .text().trim()
        val info = boxEl.select(".video-info .video-info-main .video-info-items")
            .mapNotNull { itemsEl ->
                val titleEl = itemsEl.selectFirst(".video-info-itemtitle")
                val itemEl = itemsEl.selectFirst(".video-info-item")
                if (itemEl != null && titleEl != null) {
                    val itemTitle = titleEl.text().trim()
                    val item = itemEl.text().trim().replace("\n", "")
                    if (itemTitle.isNotBlank() && item.isNotBlank()) {
                        "$itemTitle$item"
                    } else null
                } else null
            }
            .joinToString("\n")
        val playSourceList: MutableList<MediaPlaySource> = mutableListOf()
        val rows: MutableList<MediaCardRow> = mutableListOf()
        body.select("#main .content .module").forEach {
            parseModuleEl(moduleEl = it, playSourceList = playSourceList, rows = rows)
        }
        return MediaDetail(
            id = mediaId,
            title = title,
            detailUrl = detailUrl,
            subTitle = null,
            description = info,
            backgroundImageUrl = imgUrl,
            playSourceList = playSourceList,
            favoritedMediaCard = SavedMediaCard(
                id = mediaId,
                title = title,
                detailUrl = detailUrl,
                coverImageUrl = imgUrl,
                cardWidth = An1meConst.CARD_WIDTH,
                cardHeight = An1meConst.CARD_HEIGHT,
            ),
            rows = rows
        )
    }

    private fun parseModuleEl(
        moduleEl: Element,
        playSourceList: MutableList<MediaPlaySource>,
        rows: MutableList<MediaCardRow>
    ) {
        if (moduleEl.selectFirst(".module-player-tab") != null) {
            parsePlaySourceList(moduleEl = moduleEl, playSourceList = playSourceList)
        } else {
            try {
                parseOtherModule(moduleEl = moduleEl, rows = rows)
            } catch (throwable: Throwable) {
                Timber.e(throwable)
                Timber.e(moduleEl.outerHtml())
            }
        }
    }

    private fun parsePlaySourceList(
        moduleEl: Element,
        playSourceList: MutableList<MediaPlaySource>
    ) {
        val tabItemEls = moduleEl
            .select(".module-heading .module-tab .module-tab-items .module-tab-content .module-tab-item")
        val tabs = tabItemEls.map {
            val smallEl = it.selectFirst("small")
            if (smallEl != null) {
                val id = it.selectFirst("span")!!.text()
                id to "$id(${smallEl.text()})"
            } else {
                val id = it.text()
                id to id
            }
        }
        val playerListEls = moduleEl.select(".module-list.module-player-list")
        playSourceList.addAll(playerListEls.mapIndexed { index, playerListEl ->
            MediaPlaySource(
                id = tabs[index].first,
                name = tabs[index].second,
                episodeList = playerListEl
                    .select(".module-tab .module-tab-items .module-tab-content .module-blocklist a")
                    .map { aEl ->
                        val episodeName = aEl.text().trim()
                        MediaEpisode(
                            id = episodeName,
                            name = episodeName,
                            flag5 = aEl.attr("href")
                        )
                    }
            )
        })
    }

    private fun parseOtherModule(
        moduleEl: Element,
        rows: MutableList<MediaCardRow>
    ) {
        val row = MediaCardRow(
            title = moduleEl.selectFirst(".module-heading .module-title")!!.text().trim(),
            list = moduleEl.select(".module-list .module-items .module-item").map { itemEL ->
                val imgEl = itemEL.selectFirst(".module-item-cover .module-item-pic img")!!
                val aEl = itemEL.selectFirst(".module-item-titlebox a")!!
                val textEl = itemEL.selectFirst(".module-item-text")!!
                val urlPath = aEl.attr("href")
                MediaCard(
                    id = urlPath,
                    title = aEl.text().trim(),
                    detailUrl = urlPath,
                    subTitle = textEl.text().trim(),
                    coverImageUrl = imgEl.attr("data-src")
                )
            },
            cardWidth = An1meConst.CARD_WIDTH,
            cardHeight = An1meConst.CARD_HEIGHT
        )
        rows.add(row)
    }

    override suspend fun getEpisodePlayInfo(
        playSource: MediaPlaySource,
        episode: MediaEpisode
    ): MediaHttpSource {
        if (episode.flag5.isNullOrEmpty() || !episode.flag5!!.startsWith("/vodplay/")) {
            throw RuntimeException("不支持的播放地址-> ${episode.flag5}")
        }
        val url = "${an1meService.getSiteUrl()}${episode.flag5}"
        val body = url.toRequestBuild()
            .feignChrome()
            .get(okHttpClient = okHttpClient)
            .checkSuccess()
            .parseHtml()
            .body()
        val scriptEl = body.selectFirst("#main .player-wrapper script")!!
        val result = An1meConst.PLAYER_INFO_REGEX.find(scriptEl.outerHtml())
        val playerAAAAJson = result?.groups?.get(1)?.value
        if (result == null || playerAAAAJson == null) {
            Timber.e("getEpisodePlayInfo error: $url")
            throw RuntimeException("解析地址失败")
        }
        Timber.d("playerAAAA : $playerAAAAJson")
        var playerAAAA = LenientJson.decodeFromString<PlayerAAAA>(playerAAAAJson)
        if (playerAAAA.encrypt == 1) {
            playerAAAA = playerAAAA.copy(
                link = playerAAAA.link.decodeBase64ToStr(),
                linkNext = playerAAAA.linkNext.decodeBase64ToStr(),
                url = playerAAAA.url.decodeBase64ToStr(),
                urlNext = playerAAAA.urlNext.decodeBase64ToStr()
            )
        }
        return if (parseFunctionMap.keys.contains(playerAAAA.from)) {
            parseFunctionMap[playerAAAA.from]?.invoke(playerAAAA, url)
                ?: throw RuntimeException("解析地址失败")
        } else {
            createMediaHttpSource(url = playerAAAA.url)
        }
    }

    private suspend fun createMediaHttpSource(url: String, referer: String? = null): MediaHttpSource =
        MediaHttpSource(
            url = url,
            httpHeaders = mapOf(
                "User-Agent" to ChromeUserAgent,
                "Referer" to (referer ?: an1meService.getSiteUrl())
            )
        )

    private val parseFunctionMap =
        mapOf<String, suspend (PlayerAAAA, String) -> MediaHttpSource>(
            "cs3play" to { playerAAAA, referer ->
                // AU
                val bodyStr = getUrlStringContent(
                    url = "${an1meService.getSiteUrl()}/addons/dp/player/art.php?key=0&from=&id=${playerAAAA.id}&api=&url=${playerAAAA.url}&jump=",
                    referer = referer,
                    failureMsg = "spplayer 解析地址失败，请求错误"
                )
                val mediaUrl = URL_IN_JS_REGEX.find(bodyStr)?.groups?.get(1)?.value
                    ?: throw RuntimeException("spplayer 解析地址失败 url")
                createMediaHttpSource(url = mediaUrl)
            },
            "s3player" to { playerAAAA, referer ->
                // AS
                val bodyStr = getUrlStringContent(
                    url = "${an1meService.getSiteUrl()}/addons/dp/player/art.php?key=0&from=&id=${playerAAAA.id}&api=&url=${playerAAAA.url}&jump=",
                    referer = referer,
                    failureMsg = "spplayer 解析地址失败，请求错误"
                )
                val mediaUrl = URL_IN_JS_REGEX.find(bodyStr)?.groups?.get(1)?.value
                    ?: throw RuntimeException("spplayer 解析地址失败 url")
                createMediaHttpSource(url = mediaUrl)
            },
            "spplayer" to { playerAAAA, referer ->
                // AP
                val bodyStr = getUrlStringContent(
                    url = "${an1meService.getSiteUrl()}/addons/dp/player/art.php?key=0&from=&id=${playerAAAA.id}&api=&url=${playerAAAA.url}&jump=",
                    referer = referer,
                    failureMsg = "spplayer 解析地址失败，请求错误"
                )
                val mediaUrl = URL_IN_JS_REGEX.find(bodyStr)?.groups?.get(1)?.value
                    ?: throw RuntimeException("spplayer 解析地址失败 url")
                createMediaHttpSource(url = mediaUrl)
            },
            "dplayer" to { playerAAAA, referer ->
                // APP专属
                val bodyStr = getUrlStringContent(
                    url = "${an1meService.getSiteUrl()}/addons/dp/player/art.php?key=0&from=&id=${playerAAAA.id}&api=&url=${playerAAAA.url}&jump=",
                    referer = referer,
                    failureMsg = "dplayer 解析地址失败，请求错误"
                )
                val mediaUrl = URL_IN_JS_REGEX.find(bodyStr)?.groups?.get(1)?.value
                    ?: throw RuntimeException("dplayer 解析地址失败 url")
                createMediaHttpSource(url = mediaUrl)
            },
            "lzm3u8" to { playerAAAA, referer ->
                // AL
                val bodyStr = getUrlStringContent(
                    url = "${an1meService.getSiteUrl()}/addons/dp/player/art.php?key=0&from=&id=${playerAAAA.id}&api=&url=${playerAAAA.url}&jump=",
                    referer = referer,
                    failureMsg = "spplayer 解析地址失败，请求错误"
                )
                val mediaUrl = URL_IN_JS_REGEX.find(bodyStr)?.groups?.get(1)?.value
                    ?: throw RuntimeException("spplayer 解析地址失败 url")
                createMediaHttpSource(url = mediaUrl)
            },
            "rrys" to { playerAAAA, referer ->
                // RRYS
                val bodyStr = getUrlStringContent(
                    url = "${an1meService.getSiteUrl()}/addons/dp/player/art.php?key=0&from=&id=${playerAAAA.id}&api=&url=${playerAAAA.url}&jump=",
                    referer = referer,
                    failureMsg = "spplayer 解析地址失败，请求错误"
                )
                val mediaUrl = URL_IN_JS_REGEX.find(bodyStr)?.groups?.get(1)?.value
                    ?: throw RuntimeException("spplayer 解析地址失败 url")
                createMediaHttpSource(url = mediaUrl)
            },
            "hw8" to { playerAAAA, referer ->
                // AW
                val bodyStr = getUrlStringContent(
                    url = "https://player.huawei8.live/player?id=${playerAAAA.id}&url=${playerAAAA.url}",
                    referer = referer,
                    failureMsg = "hw8 解析地址失败，请求错误"
                )
                val mediaUrl = URL_IN_JS_REGEX.find(bodyStr)?.groups?.get(1)?.value
                    ?: throw RuntimeException("hw8 解析地址失败 url")
                createMediaHttpSource(url = mediaUrl)
            },
            "heimuer" to { playerAAAA, referer ->
                // AH
                val bodyStr = getUrlStringContent(
                    url = "${an1meService.getSiteUrl()}/addons/dp/player/art.php?key=0&from=&id=${playerAAAA.id}&api=&url=${playerAAAA.url}&jump=",
                    referer = referer,
                    failureMsg = "解析地址失败，请求错误"
                )
                val mediaUrl = URL_IN_JS_REGEX.find(bodyStr)?.groups?.get(1)?.value
                    ?: throw RuntimeException("解析地址失败 url")
                createMediaHttpSource(url = mediaUrl)
            },
        )

    private fun getUrlStringContent(
        url: String,
        referer: String? = null,
        failureMsg: String = "请求失败"
    ): String {
        val resp = url.toRequestBuild()
            .feignChrome(referer = referer)
            .get(okHttpClient = okHttpClient)
            .checkSuccess {
                if (!it.isSuccessful) throw RuntimeException(failureMsg)
            }
        val bodyStr = resp.body?.string() ?: throw RuntimeException(failureMsg)
        return bodyStr
    }

    companion object {
        private val URL_IN_JS_REGEX = "[\"']?url[\"']?: [\"'](http.*?)[\"'],".toRegex()
    }
}