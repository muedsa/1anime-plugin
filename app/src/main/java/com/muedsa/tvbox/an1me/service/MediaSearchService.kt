package com.muedsa.tvbox.an1me.service

import com.muedsa.tvbox.an1me.An1meConst
import com.muedsa.tvbox.api.data.MediaCard
import com.muedsa.tvbox.api.data.MediaCardRow
import com.muedsa.tvbox.api.service.IMediaSearchService
import com.muedsa.tvbox.tool.checkSuccess
import com.muedsa.tvbox.tool.feignChrome
import com.muedsa.tvbox.tool.get
import com.muedsa.tvbox.tool.parseHtml
import com.muedsa.tvbox.tool.toRequestBuild
import okhttp3.OkHttpClient

class MediaSearchService(
    private val an1meService: An1meService,
    private val okHttpClient: OkHttpClient
) : IMediaSearchService {

    override suspend fun searchMedias(query: String): MediaCardRow {
        val body = "${an1meService.getSiteUrl()}/vodsearch/-------------.html?wd=$query".toRequestBuild()
            .feignChrome()
            .get(okHttpClient = okHttpClient)
            .checkSuccess()
            .parseHtml()
            .body()
        val moduleEl = body.selectFirst("#main .content .module")
            ?: return MediaCardRow(
                title = "search",
                list = emptyList(),
                cardWidth = An1meConst.CARD_WIDTH,
                cardHeight = An1meConst.CARD_HEIGHT
            )
        val itemEls = moduleEl.select(".module-list .module-items .module-search-item")
        return MediaCardRow(
            title = "search",
            list = itemEls.map { itemEl ->
                val imgEl = itemEl.selectFirst(".video-cover .module-item-pic img")!!
                val videoInfoHeaderEl = itemEl.selectFirst(".video-info .video-info-header")!!
                val aEl = videoInfoHeaderEl.selectFirst("h3 a")!!
                val urlPath = aEl.attr("href")
                MediaCard(
                    id = urlPath,
                    title = aEl.text().trim(),
                    detailUrl = urlPath,
                    subTitle = videoInfoHeaderEl.selectFirst(".video-serial")?.text(),
                    coverImageUrl = imgEl.attr("data-src")
                )
            },
            cardWidth = An1meConst.CARD_WIDTH,
            cardHeight = An1meConst.CARD_HEIGHT
        )
    }
}