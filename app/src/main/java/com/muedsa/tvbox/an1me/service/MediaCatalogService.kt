package com.muedsa.tvbox.an1me.service

import com.muedsa.tvbox.an1me.An1meConst
import com.muedsa.tvbox.api.data.MediaCard
import com.muedsa.tvbox.api.data.MediaCatalogConfig
import com.muedsa.tvbox.api.data.MediaCatalogOption
import com.muedsa.tvbox.api.data.PagingResult
import com.muedsa.tvbox.api.service.IMediaCatalogService
import com.muedsa.tvbox.tool.checkSuccess
import com.muedsa.tvbox.tool.feignChrome
import com.muedsa.tvbox.tool.get
import com.muedsa.tvbox.tool.parseHtml
import com.muedsa.tvbox.tool.toRequestBuild
import okhttp3.OkHttpClient

class MediaCatalogService(
    private val an1meService: An1meService,
    private val okHttpClient: OkHttpClient,
) : IMediaCatalogService {

    override suspend fun getConfig(): MediaCatalogConfig =
        MediaCatalogConfig(
            initKey = "1",
            pageSize = 48,
            cardWidth = An1meConst.CARD_WIDTH,
            cardHeight = An1meConst.CARD_HEIGHT,
            catalogOptions = An1meConst.CATALOG_OPTIONS
        )

    override suspend fun catalog(
        options: List<MediaCatalogOption>,
        loadKey: String,
        loadSize: Int
    ): PagingResult<MediaCard> {
        val page = loadKey.toInt()
        val category = options.findOptionFirstValue("category", defaultValue = "")
        val genre = options.findOptionFirstValue("genre", defaultValue = "")
        val region = options.findOptionFirstValue("region", defaultValue = "")
        val year = options.findOptionFirstValue("year", defaultValue = "")
        val language = options.findOptionFirstValue("language", defaultValue = "")
        val order = options.findOptionFirstValue("order", defaultValue = "")
        val url =
            "${an1meService.getSiteUrl()}/vodshow/$category-$region-$order-$genre-$language----$page---$year.html"
        val body = url.toRequestBuild()
            .feignChrome()
            .get(okHttpClient = okHttpClient)
            .checkSuccess()
            .parseHtml()
            .body()
        val cards =
            body.select("#main >.content >.module .module-list:first-child .module-items .module-item")
                .map { itemEl ->
                    val imgEl = itemEl.selectFirst(".module-item-pic img")!!
                    val aEl = itemEl.selectFirst(".module-item-content .video-name a")!!
                    val urlPath = aEl.attr("href")
                    MediaCard(
                        id = urlPath,
                        title = aEl.text().trim(),
                        detailUrl = urlPath,
                        subTitle = itemEl.selectFirst(".module-item-text")?.text(),
                        coverImageUrl = imgEl.attr("data-src")
                    )
                }
        val pageLinkEls = body.select("#main >.content >.module >.module-footer #page a")
        return PagingResult(
            list = cards,
            prevKey = pageLinkEls.find { it.text().contains("上一页") }?.let{
                val p = urlToPage(it.attr("href"))
                if (p == loadKey) null else p
            },
            nextKey = pageLinkEls.find { it.text().contains("下一页") }?.let{
                val p = urlToPage(it.attr("href"))
                if (p == loadKey) null else p
            }
        )
    }

    companion object {
        val URL_PAGE_REGEX = "/vodshow/\\w*-\\w*-\\w*-\\w*-\\w*-\\w*---(\\w*)---\\w*.html".toRegex()

        fun List<MediaCatalogOption>.findOptionFirstValue(
            optionValue: String,
            defaultValue: String
        ): String {
            val option = find { it.value == optionValue }
            return if (option != null && option.items.isNotEmpty()) {
                option.items[0].value
            } else defaultValue
        }

        fun urlToPage(url: String) = URL_PAGE_REGEX.find(url)?.groups[1]?.value ?: "1"
    }
}