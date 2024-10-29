package com.muedsa.tvbox.an1me

import com.muedsa.tvbox.an1me.service.MainScreenService
import com.muedsa.tvbox.an1me.service.MediaDetailService
import com.muedsa.tvbox.an1me.service.MediaSearchService
import com.muedsa.tvbox.api.plugin.IPlugin
import com.muedsa.tvbox.api.plugin.PluginOptions
import com.muedsa.tvbox.api.plugin.TvBoxContext
import com.muedsa.tvbox.api.service.IMainScreenService
import com.muedsa.tvbox.api.service.IMediaDetailService
import com.muedsa.tvbox.api.service.IMediaSearchService

class An1mePlugin(tvBoxContext: TvBoxContext) : IPlugin(tvBoxContext = tvBoxContext) {
    override var options: PluginOptions = PluginOptions(enableDanDanPlaySearch = true)

    private val mainScreenService by lazy { MainScreenService() }
    private val mediaDetailService by lazy { MediaDetailService() }
    private val mediaSearchService by lazy { MediaSearchService() }
    override fun provideMainScreenService(): IMainScreenService = mainScreenService
    override fun provideMediaDetailService(): IMediaDetailService = mediaDetailService
    override fun provideMediaSearchService(): IMediaSearchService = mediaSearchService

    override suspend fun onInit() {}
    override suspend fun onLaunched() {}
}