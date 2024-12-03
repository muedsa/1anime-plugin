package com.muedsa.tvbox.an1me.service

import com.muedsa.tvbox.an1me.TestPlugin
import com.muedsa.tvbox.an1me.checkMediaCard
import com.muedsa.tvbox.api.data.MediaCardType
import kotlinx.coroutines.test.runTest
import org.junit.Test

class MediaDetailServiceTest {

    private val service = TestPlugin.provideMediaDetailService()

    @Test
    fun getDetailData_test() = runTest{
        val detail = service.getDetailData("/voddetail/8221.html", "/voddetail/8221.html")
        check(detail.id.isNotEmpty())
        check(detail.title.isNotEmpty())
        check(detail.detailUrl.isNotEmpty())
        check(detail.backgroundImageUrl.isNotEmpty())
        println("${detail.id} ${detail.title} ${detail.description}")
        detail.favoritedMediaCard?.let { favoritedMediaCard ->
            checkMediaCard(favoritedMediaCard, cardType = MediaCardType.STANDARD)
            check(favoritedMediaCard.cardWidth > 0)
            check(favoritedMediaCard.cardHeight > 0)
        }
        check(detail.playSourceList.isNotEmpty())
        detail.playSourceList.forEach { mediaPlaySource ->
            check(mediaPlaySource.id.isNotEmpty())
            check(mediaPlaySource.name.isNotEmpty())
            check(mediaPlaySource.episodeList.isNotEmpty())
            mediaPlaySource.episodeList.forEach {
                check(it.id.isNotEmpty())
                check(it.name.isNotEmpty())
                println("${mediaPlaySource.name}->${it.id}-${it.name}: ${it.flag5}")
            }
        }
    }

    @Test
    fun getEpisodePlayInfo_test() = runTest{
        val detail = service.getDetailData("/voddetail/8221.html", "/voddetail/8221.html")
        check(detail.playSourceList.isNotEmpty())
        check(detail.playSourceList.flatMap { it.episodeList }.isNotEmpty())
        val mediaPlaySource = detail.playSourceList[0]
        val mediaEpisode = mediaPlaySource.episodeList[0]
        val playInfo = service.getEpisodePlayInfo(mediaPlaySource, mediaEpisode)
        check(playInfo.url.isNotEmpty())
        println(playInfo.url)
    }


    @Test
    fun getDplayerEpisodePlayInfo_test() = runTest {
        val detail = service.getDetailData("/voddetail/8221.html", "/voddetail/8221.html")
        check(detail.playSourceList.isNotEmpty())
        check(detail.playSourceList.flatMap { it.episodeList }.isNotEmpty())
        val mediaPlaySource = detail.playSourceList.find { it.id == "APP专属" }!!
        val mediaEpisode = mediaPlaySource.episodeList[0]
        val playInfo = service.getEpisodePlayInfo(mediaPlaySource, mediaEpisode)
        check(playInfo.url.isNotEmpty())
        println(playInfo.url)
    }
}