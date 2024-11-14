package com.muedsa.tvbox.an1me

import com.muedsa.tvbox.api.plugin.TvBoxContext
import com.muedsa.tvbox.tool.IPv6Checker

val TestPlugin by lazy {
    An1mePlugin(
        tvBoxContext = TvBoxContext(
            screenWidth = 1920,
            screenHeight = 1080,
            debug = true,
            store = FakePluginPrefStore(),
            iPv6Status = IPv6Checker.checkIPv6Support()
        )
    )
}