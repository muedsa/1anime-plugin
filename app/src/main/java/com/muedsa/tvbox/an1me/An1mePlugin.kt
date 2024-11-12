package com.muedsa.tvbox.an1me

import com.muedsa.tvbox.an1me.service.An1meService
import com.muedsa.tvbox.an1me.service.MainScreenService
import com.muedsa.tvbox.an1me.service.MediaDetailService
import com.muedsa.tvbox.an1me.service.MediaSearchService
import com.muedsa.tvbox.api.plugin.IPlugin
import com.muedsa.tvbox.api.plugin.PluginOptions
import com.muedsa.tvbox.api.plugin.TvBoxContext
import com.muedsa.tvbox.api.service.IMainScreenService
import com.muedsa.tvbox.api.service.IMediaDetailService
import com.muedsa.tvbox.api.service.IMediaSearchService
import com.muedsa.tvbox.tool.PluginCookieJar
import com.muedsa.tvbox.tool.SharedCookieSaver
import com.muedsa.tvbox.tool.createOkHttpClient

class An1mePlugin(tvBoxContext: TvBoxContext) : IPlugin(tvBoxContext = tvBoxContext) {
    override var options: PluginOptions = PluginOptions(enableDanDanPlaySearch = true)

    private val okHttpClient by lazy {
        createOkHttpClient(
            debug = tvBoxContext.debug,
            cookieJar = PluginCookieJar(
                saver = SharedCookieSaver(store = tvBoxContext.store)
            )
        )
    }
    private val an1meService by lazy { An1meService(okHttpClient = okHttpClient) }
    private val mainScreenService by lazy {
        MainScreenService(
            an1meService = an1meService,
            okHttpClient = okHttpClient
        )
    }
    private val mediaDetailService by lazy {
        MediaDetailService(
            an1meService = an1meService,
            okHttpClient = okHttpClient
        )
    }
    private val mediaSearchService by lazy {
        MediaSearchService(
            an1meService = an1meService,
            okHttpClient = okHttpClient
        )
    }
    override fun provideMainScreenService(): IMainScreenService = mainScreenService
    override fun provideMediaDetailService(): IMediaDetailService = mediaDetailService
    override fun provideMediaSearchService(): IMediaSearchService = mediaSearchService

    override suspend fun onInit() {}
    override suspend fun onLaunched() {}
}