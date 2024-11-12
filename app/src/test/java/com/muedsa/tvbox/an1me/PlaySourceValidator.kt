package com.muedsa.tvbox.an1me

import com.muedsa.tvbox.an1me.service.An1meService
import com.muedsa.tvbox.tool.createOkHttpClient
import kotlinx.coroutines.test.runTest
import okhttp3.Request
import org.junit.Test

class PlaySourceValidator {

    private val okHttpClient = createOkHttpClient(debug = true)
    private val an1meService = An1meService(okHttpClient = okHttpClient)

    @Test
    fun addonsDpPlayerIndexPhp_valid() = runTest {
        val content = getUrlStringContent("${an1meService.getSiteUrl()}/addons/dp/player/index.php?key=0&id=&url=")
        check(content.contains("window.location.href=\"/addons/dp/player/art.php?key=0&from=&id=&api=&url=&jump="))
    }

    @Test
    fun cs3play__valid() = runTest {
        // AU
        val content = getUrlStringContent(getScriptUrl("cs3play"))
        check(content.contains("src=\"/addons/dp/player/index.php?key=0&id='+MacPlayer.Id+'&url='+MacPlayer.PlayUrl+'\""))
    }

    @Test
    fun s3player_valid() = runTest {
        // AS
        val content = getUrlStringContent(getScriptUrl("s3player"))
        check(content.contains("src=\"/addons/dp/player/index.php?key=0&id='+MacPlayer.Id+'&url='+MacPlayer.PlayUrl+'\""))
    }

    @Test
    fun spplayer_valid() = runTest {
        // AP
        val content = getUrlStringContent(getScriptUrl("spplayer"))
        check(content.contains("src=\"/addons/dp/player/index.php?key=0&id='+MacPlayer.Id+'&url='+MacPlayer.PlayUrl+'\""))
    }

    @Test
    fun heimuer_valid() = runTest {
        // AH
        val content = getUrlStringContent(getScriptUrl("heimuer"))
        check(content.contains("src=\"/addons/dp/player/index.php?key=0&id='+MacPlayer.Id+'&url='+MacPlayer.PlayUrl+'\""))
    }

    @Test
    fun dplayer_valid() = runTest {
        // APP专属
        val content = getUrlStringContent(getScriptUrl("dplayer"))
        check(content.contains("src=\"/addons/dp/player/index.php?key=0&id='+MacPlayer.Id+'&url='+MacPlayer.PlayUrl+'\""))
    }

    @Test
    fun lzm3u8_valid() = runTest {
        // AL
        val content = getUrlStringContent(getScriptUrl("lzm3u8"))
        check(content.contains("src=\"/addons/dp/player/index.php?key=0&id='+MacPlayer.Id+'&url='+MacPlayer.PlayUrl+'\""))
    }

    @Test
    fun rrys_valid() = runTest {
        // RRYS
        val content = getUrlStringContent(getScriptUrl("rrys"))
        check(content.contains("src=\"/addons/dp/player/index.php?key=0&id='+MacPlayer.Id+'&url='+MacPlayer.PlayUrl+'\""))
    }

    @Test
    fun hw8_valid() = runTest {
        // AW
        val content = getUrlStringContent(getScriptUrl("hw8"))
        check(content.contains("src=\"https://player.huawei8.live/player?url='+MacPlayer.Id+'&url='+MacPlayer.PlayUrl+'\""))
    }

    private suspend fun getScriptUrl(source: String): String =
        "${an1meService.getSiteUrl()}/static/player/$source.js"

    private fun getUrlStringContent(url: String): String {
        val resp = okHttpClient.newCall(Request.Builder().url(url).build()).execute()
        if(!resp.isSuccessful) throw RuntimeException("请求失败: $url")
        return resp.body!!.string()
    }
}