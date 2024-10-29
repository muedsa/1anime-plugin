package com.muedsa.tvbox.an1me.model

import kotlinx.serialization.Serializable

@Serializable
data class DplayerResp<T>(
    val code: Int = 0,
    val data: T? = null,
    val msg: String = ""
)