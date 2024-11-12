package com.muedsa.tvbox.an1me.service

import com.muedsa.tvbox.tool.feignChrome
import com.muedsa.tvbox.tool.get
import com.muedsa.tvbox.tool.parseHtml
import com.muedsa.tvbox.tool.toRequestBuild
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import okhttp3.HttpUrl.Companion.toHttpUrl
import okhttp3.OkHttpClient
import timber.log.Timber

class An1meService(
    private val okHttpClient: OkHttpClient
) {
    private var siteUrl: String? = null
    private val mutex = Mutex()

    suspend fun getSiteUrl(): String = mutex.withLock {
        if (siteUrl == null) {
            val body = "https://1anime.org/".toRequestBuild()
                .feignChrome()
                .header("Connection", "close") // 这里使用http1.1
                .header("http.protocol", "http/1.1") // 这里使用http1.1
                .get(okHttpClient = okHttpClient)
                .parseHtml()
                .body()
            val links = body.select(".release-main .links ul li a[href]")
                .map { it.attr("href") }
                .filter { it.isNotBlank() }
            links.forEach {
                Timber.d("scan link: $it")
            }
            val link = if(links.isNotEmpty()) {
                if (links.size > 1) links[1] else links[0]
            } else throw RuntimeException("从发布页获取可用地址失败")
            siteUrl = link.toHttpUrl()
                .newBuilder()
                .query(null)
                .build()
                .resolve("/")
                .toString()
                .removeSuffix("/")
            Timber.d("set siteUrl: $siteUrl")
        }
        return siteUrl!!
    }
}