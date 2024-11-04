package com.muedsa.tvbox.an1me.service

import com.muedsa.tvbox.tool.feignChrome
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import okhttp3.HttpUrl.Companion.toHttpUrl
import org.jsoup.Jsoup
import timber.log.Timber

class An1meService {
    private var siteUrl: String? = null
    private val mutex = Mutex()

    suspend fun getSiteUrl(): String = mutex.withLock {
        if (siteUrl == null) {
            val body = Jsoup.connect("https://1anime.org/")
                .feignChrome()
                .get()
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