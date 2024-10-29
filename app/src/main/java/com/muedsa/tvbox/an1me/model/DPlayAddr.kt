package com.muedsa.tvbox.an1me.model

import kotlinx.serialization.Serializable

@Serializable
data class DPlayAddr(
    val title: String = "",
    val addr: String = "",
    val m3u8FileDomain: String = ""
)