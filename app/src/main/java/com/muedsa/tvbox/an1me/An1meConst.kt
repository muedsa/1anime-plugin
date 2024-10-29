package com.muedsa.tvbox.an1me

object An1meConst {
    const val URL = "https://acg.d1dm.top"
    const val CARD_WIDTH = 125
    const val CARD_HEIGHT = 175
    const val NOT_IMAGE_CARD_WIDTH = 200
    const val NOT_IMAGE_CARD_HEIGHT = 70
    val CARD_COLORS = listOf(
        0XFF_15_5A_32,
        0XFF_09_53_45,
        0XFF_15_43_61,
        0XFF_42_49_49,
        0XFF_78_42_13
    )
    val NOT_SUPPORT_ROW = listOf("专题", "图文", "最新影片")

    val PLAYER_INFO_REGEX = "<script type=\"text/javascript\">var player_aaaa=(\\{.*?\\})</script>".toRegex()

    val DPLAYER_VIDEO_TOKEN_REGEX = "\\(null,\"\",\"(\\w+)\",\"\\\\u002F\"\\)\\);".toRegex()
}