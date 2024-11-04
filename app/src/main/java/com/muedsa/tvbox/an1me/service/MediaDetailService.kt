package com.muedsa.tvbox.an1me.service

import com.muedsa.tvbox.an1me.An1meConst
import com.muedsa.tvbox.an1me.model.DPlayAddrs
import com.muedsa.tvbox.an1me.model.DplayerResp
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
import com.muedsa.tvbox.tool.feignChrome
import okhttp3.HttpUrl.Companion.toHttpUrl
import org.jsoup.Connection.Method
import org.jsoup.Jsoup
import org.jsoup.nodes.Element
import timber.log.Timber
import java.net.CookieStore

class MediaDetailService(
    private val cookieStore: CookieStore
) : IMediaDetailService {

    override suspend fun getDetailData(mediaId: String, detailUrl: String): MediaDetail {
        if (!detailUrl.startsWith("/voddetail/")) {
            throw RuntimeException("不支持的类型 $detailUrl")
        }
        val body = Jsoup.connect("${An1meConst.URL}$detailUrl")
            .feignChrome(cookieStore = cookieStore)
            .get()
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
                Timber.e("parseOtherModule error", throwable)
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
        val url = "${An1meConst.URL}${episode.flag5}"
        val body = Jsoup.connect(url)
            .feignChrome(cookieStore = cookieStore)
            .get()
            .body()
        val scriptEl = body.selectFirst("#main .player-wrapper script")!!
        val result = An1meConst.PLAYER_INFO_REGEX.find(scriptEl.outerHtml())
        val playerAAAAJson = result?.groups?.get(1)?.value
        if (result == null || playerAAAAJson == null) {
            Timber.e("getEpisodePlayInfo error: $url")
            throw RuntimeException("解析地址失败")
        }
        Timber.d("playerAAAA : $playerAAAAJson")
        val playerAAAA = LenientJson.decodeFromString<PlayerAAAA>(playerAAAAJson)
        return if (playerAAAA.url.endsWith(suffix = ".m3u8", ignoreCase = true)) {
            MediaHttpSource(url = playerAAAA.url)
        } else if (playerAAAA.from == "dplayer") {
            getHttpSourceFromDplayer(playerAAAA.url)
        } else {
            MediaHttpSource(url = playerAAAA.url)
        }
    }

    private fun getHttpSourceFromDplayer(url: String): MediaHttpSource {
        val body = Jsoup.connect(url)
            .feignChrome(cookieStore = cookieStore)
            .get()
            .body()
        val token = An1meConst.DPLAYER_VIDEO_TOKEN_REGEX.find(body.html())?.groups?.get(1)?.value
        if (token.isNullOrEmpty()) {
            Timber.e("getHttpSourceFromDplayer error: $url")
            throw RuntimeException("解析播放地址")
        }
        val frameUrl = url.toHttpUrl()
        val getUrl = frameUrl.newBuilder("/vod/within/playaddr/get")!!
            .addQueryParameter("requestId", token)
            .addQueryParameter("vcode", frameUrl.queryParameter("vcode"))
            .build()
            .toString()
        val respStr = Jsoup.connect(getUrl)
            .feignChrome(cookieStore = cookieStore)
            .method(Method.GET)
            .execute()
            .body()
        Timber.d(respStr)
        val resp = LenientJson.decodeFromString<DplayerResp<DPlayAddrs>>(respStr)
        val playAddrs = resp.data?.playAddr
        if (playAddrs.isNullOrEmpty()) {
            throw RuntimeException("解析播放地址")
        }
        val playAddr = playAddrs[0]
        return MediaHttpSource(
            url = "${playAddr.m3u8FileDomain}${playAddr.addr}",
            httpHeaders = mapOf(
                "User-Agent" to ChromeUserAgent,
                "Referer" to url
            )
        )
    }
}