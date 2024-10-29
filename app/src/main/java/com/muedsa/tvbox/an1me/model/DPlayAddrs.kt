package com.muedsa.tvbox.an1me.model

import kotlinx.serialization.Serializable

@Serializable
data class DPlayAddrs(
    val playAddr: List<DPlayAddr> = emptyList()
)