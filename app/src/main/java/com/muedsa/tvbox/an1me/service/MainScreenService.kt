package com.muedsa.tvbox.an1me.service

import com.muedsa.tvbox.an1me.An1meConst
import com.muedsa.tvbox.api.data.MediaCard
import com.muedsa.tvbox.api.data.MediaCardRow
import com.muedsa.tvbox.api.data.MediaCardType
import com.muedsa.tvbox.api.service.IMainScreenService
import com.muedsa.tvbox.tool.checkSuccess
import com.muedsa.tvbox.tool.feignChrome
import com.muedsa.tvbox.tool.get
import com.muedsa.tvbox.tool.parseHtml
import com.muedsa.tvbox.tool.toRequestBuild
import okhttp3.OkHttpClient
import org.jsoup.nodes.Element

class MainScreenService(
    private val an1meService: An1meService,
    private val okHttpClient: OkHttpClient
) : IMainScreenService {

    override suspend fun getRowsData(): List<MediaCardRow> {
        val body = "${an1meService.getSiteUrl()}/".toRequestBuild()
            .feignChrome()
            .get(okHttpClient = okHttpClient)
            .checkSuccess()
            .parseHtml()
            .body()
        val rows: MutableList<MediaCardRow> = mutableListOf()
        body.select("#index-main >.content >.module")
            .filter { !it.hasClass("homepage_homepage_channelnav") && !it.hasClass("module-bg") }
            .forEach { moduleEl ->
                parseModuleEl(moduleEl = moduleEl, rows = rows)
            }
        return rows
    }

    private fun parseModuleEl(moduleEl: Element, rows: MutableList<MediaCardRow>) {
        if (moduleEl.selectFirst(">.module-heading") != null) {
            parseModuleMainEl(mainEl = moduleEl, rows = rows)
        } else {
            var mainEl = moduleEl.selectFirst(">.module-main")
            val sideEl = moduleEl.selectFirst(">.module-side")
            if (mainEl != null) {
                parseModuleMainEl(mainEl = mainEl, rows = rows)
            }
            if (sideEl != null) {
                parseModuleSideEl(sideEl = sideEl, rows = rows)
            }
        }
    }

    private fun parseModuleMainEl(mainEl: Element, rows: MutableList<MediaCardRow>) {
        val titleEl = mainEl.selectFirst(">.module-heading >.module-title")!!
        var rowTitle = titleEl.text().trim()
        if (An1meConst.NOT_SUPPORT_ROW.contains(rowTitle)) {
            return
        }
        mainEl.selectFirst(">.module-heading >.module-heading")?.let {
            rowTitle = "$rowTitle ${it.text().trim()}"
        }
        val itemEls = mainEl.select(".module-list >.module-items .module-item")
        if (itemEls.isNotEmpty()) {
            val row = MediaCardRow(
                title = rowTitle,
                list = itemEls.map { itemEL ->
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
    }

    private fun parseModuleSideEl(sideEl: Element, rows: MutableList<MediaCardRow>) {
        val titleEl = sideEl.selectFirst(">.module-heading >.module-title")!!
        val rowTitle = titleEl.text().trim()
        if (An1meConst.NOT_SUPPORT_ROW.contains(rowTitle)) {
            return
        }
        val aEls = sideEl.select(">.module-side-list .module-textlist a")
        if (aEls.isNotEmpty()) {
            val rowIndex = rows.count { c -> c.cardType == MediaCardType.NOT_IMAGE }
            val row = MediaCardRow(
                title = rowTitle,
                list = aEls.mapIndexed { index, aEl ->
                    val urlPath = aEl.attr("href")
                    MediaCard(
                        id = urlPath,
                        title = aEl.selectFirst(".text-list-title h3")!!.text().trim(),
                        detailUrl = urlPath,
                        subTitle = aEl.selectFirst(".text-list-title p")?.text()?.trim(),
                        backgroundColor = An1meConst.CARD_COLORS[(index + rowIndex) % An1meConst.CARD_COLORS.size]
                    )
                },
                cardWidth = An1meConst.NOT_IMAGE_CARD_WIDTH,
                cardHeight = An1meConst.NOT_IMAGE_CARD_HEIGHT,
                cardType = MediaCardType.NOT_IMAGE
            )
            rows.add(row)
        }
    }
}