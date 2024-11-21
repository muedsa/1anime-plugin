package com.muedsa.tvbox.an1me

import com.muedsa.tvbox.api.data.MediaCatalogOption
import com.muedsa.tvbox.api.data.MediaCatalogOptionItem
import com.muedsa.tvbox.tool.decodeBase64ToStr
import java.util.Calendar

object An1meConst {
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

    val CATALOG_OPTIONS = listOf(
        MediaCatalogOption(
            name = "类型",
            value = "category",
            required = true,
            items = listOf(
                MediaCatalogOptionItem(
                    name = "TV动画",
                    value = "1",
                    defaultChecked = true,
                ),
                MediaCatalogOptionItem(
                    name = "剧场版",
                    value = "2",
                ),
                MediaCatalogOptionItem(
                    name = "绅士",
                    value = "3",
                ),
                MediaCatalogOptionItem(
                    name = "电影",
                    value = "4",
                ),
            )
        ),
        MediaCatalogOption(
            name = "剧情",
            value = "genre",
            required = true,
            items = listOf(
                MediaCatalogOptionItem(
                    name = "全部",
                    value = "",
                    defaultChecked = true,
                ),
                MediaCatalogOptionItem(
                    name = "56aB55Wq".decodeBase64ToStr(),
                    value = "56aB55Wq".decodeBase64ToStr(),
                ),
                MediaCatalogOptionItem(
                    name = "5Y2W6IKJ".decodeBase64ToStr(),
                    value = "5Y2W6IKJ".decodeBase64ToStr(),
                ),
                MediaCatalogOptionItem(
                    name = "5ZCO5a6r".decodeBase64ToStr(),
                    value = "5ZCO5a6r".decodeBase64ToStr(),
                ),
                MediaCatalogOptionItem(
                    name = "异世界",
                    value = "异世界",
                ),
                MediaCatalogOptionItem(
                    name = "战斗",
                    value = "战斗",
                ),
                MediaCatalogOptionItem(
                    name = "热血",
                    value = "热血",
                ),
                MediaCatalogOptionItem(
                    name = "校园",
                    value = "校园",
                ),
                MediaCatalogOptionItem(
                    name = "穿越",
                    value = "穿越",
                ),
                MediaCatalogOptionItem(
                    name = "搞笑",
                    value = "搞笑",
                ),
                MediaCatalogOptionItem(
                    name = "奇幻",
                    value = "奇幻",
                ),
                MediaCatalogOptionItem(
                    name = "萌系",
                    value = "萌系",
                ),
                MediaCatalogOptionItem(
                    name = "魔法",
                    value = "魔法",
                ),
                MediaCatalogOptionItem(
                    name = "推理",
                    value = "推理",
                ),
                MediaCatalogOptionItem(
                    name = "乙女",
                    value = "乙女",
                ),
                MediaCatalogOptionItem(
                    name = "萝莉",
                    value = "萝莉",
                ),
                MediaCatalogOptionItem(
                    name = "运动",
                    value = "运动",
                ),
                MediaCatalogOptionItem(
                    name = "美食",
                    value = "美食",
                ),
                MediaCatalogOptionItem(
                    name = "机甲",
                    value = "机甲",
                ),
                MediaCatalogOptionItem(
                    name = "魔法女性向",
                    value = "魔法女性向",
                ),
                MediaCatalogOptionItem(
                    name = "BL",
                    value = "BL",
                ),
                MediaCatalogOptionItem(
                    name = "百合",
                    value = "百合",
                ),
                MediaCatalogOptionItem(
                    name = "5peg56CB".decodeBase64ToStr(),
                    value = "5peg56CB".decodeBase64ToStr(),
                ),
                MediaCatalogOptionItem(
                    name = "6L+R5Lqy".decodeBase64ToStr(),
                    value = "6L+R5Lqy".decodeBase64ToStr(),
                ),
                MediaCatalogOptionItem(
                    name = "5aeQ".decodeBase64ToStr(),
                    value = "5aeQ".decodeBase64ToStr(),
                ),
                MediaCatalogOptionItem(
                    name = "5aa5".decodeBase64ToStr(),
                    value = "5aa5".decodeBase64ToStr(),
                ),
                MediaCatalogOptionItem(
                    name = "5q+N".decodeBase64ToStr(),
                    value = "5q+N".decodeBase64ToStr(),
                ),
                MediaCatalogOptionItem(
                    name = "5aWz5YS/".decodeBase64ToStr(),
                    value = "5aWz5YS/".decodeBase64ToStr(),
                ),
                MediaCatalogOptionItem(
                    name = "5biI55Sf".decodeBase64ToStr(),
                    value = "5biI55Sf".decodeBase64ToStr(),
                ),
                MediaCatalogOptionItem(
                    name = "5oOF5L6j".decodeBase64ToStr(),
                    value = "5oOF5L6j".decodeBase64ToStr(),
                ),
                MediaCatalogOptionItem(
                    name = "6Z2S5qKF56u56ams".decodeBase64ToStr(),
                    value = "6Z2S5qKF56u56ams".decodeBase64ToStr(),
                ),
                MediaCatalogOptionItem(
                    name = "5ZCM5LqL".decodeBase64ToStr(),
                    value = "5ZCM5LqL".decodeBase64ToStr(),
                ),
                MediaCatalogOptionItem(
                    name = "Sks=".decodeBase64ToStr(),
                    value = "Sks=".decodeBase64ToStr(),
                ),
                MediaCatalogOptionItem(
                    name = "5aSE5aWz".decodeBase64ToStr(),
                    value = "5aSE5aWz".decodeBase64ToStr(),
                ),
                MediaCatalogOptionItem(
                    name = "5b6h5aeQ".decodeBase64ToStr(),
                    value = "5b6h5aeQ".decodeBase64ToStr(),
                ),
                MediaCatalogOptionItem(
                    name = "54af5aWz".decodeBase64ToStr(),
                    value = "54af5aWz".decodeBase64ToStr(),
                ),
                MediaCatalogOptionItem(
                    name = "5Lq65aa7".decodeBase64ToStr(),
                    value = "5Lq65aa7".decodeBase64ToStr(),
                ),
                MediaCatalogOptionItem(
                    name = "6ICB5biI".decodeBase64ToStr(),
                    value = "6ICB5biI".decodeBase64ToStr(),
                ),
                MediaCatalogOptionItem(
                    name = "5aWz5Yy75oqk5aOr".decodeBase64ToStr(),
                    value = "5aWz5Yy75oqk5aOr".decodeBase64ToStr(),
                ),
                MediaCatalogOptionItem(
                    name = "T0w=".decodeBase64ToStr(),
                    value = "T0w=".decodeBase64ToStr(),
                ),
                MediaCatalogOptionItem(
                    name = "5aSn5bCP5aeQ".decodeBase64ToStr(),
                    value = "5aSn5bCP5aeQ".decodeBase64ToStr(),
                ),
                MediaCatalogOptionItem(
                    name = "5YG25YOP".decodeBase64ToStr(),
                    value = "5YG25YOP".decodeBase64ToStr(),
                ),
                MediaCatalogOptionItem(
                    name = "5aWz5LuG".decodeBase64ToStr(),
                    value = "5aWz5LuG".decodeBase64ToStr(),
                ),
                MediaCatalogOptionItem(
                    name = "5ber5aWz".decodeBase64ToStr(),
                    value = "5ber5aWz".decodeBase64ToStr(),
                ),
                MediaCatalogOptionItem(
                    name = "5L+u5aWz".decodeBase64ToStr(),
                    value = "5L+u5aWz".decodeBase64ToStr(),
                ),
                MediaCatalogOptionItem(
                    name = "6aOO5L+X5aiY".decodeBase64ToStr(),
                    value = "6aOO5L+X5aiY".decodeBase64ToStr(),
                ),
                MediaCatalogOptionItem(
                    name = "5YWs5Li7".decodeBase64ToStr(),
                    value = "5YWs5Li7".decodeBase64ToStr(),
                ),
                MediaCatalogOptionItem(
                    name = "5aWz5oiY5aOr".decodeBase64ToStr(),
                    value = "5aWz5oiY5aOr".decodeBase64ToStr(),
                ),
                MediaCatalogOptionItem(
                    name = "6a2U5rOV5bCR5aWz".decodeBase64ToStr(),
                    value = "6a2U5rOV5bCR5aWz".decodeBase64ToStr(),
                ),
                MediaCatalogOptionItem(
                    name = "5byC56eN5peP".decodeBase64ToStr(),
                    value = "5byC56eN5peP".decodeBase64ToStr(),
                ),
                MediaCatalogOptionItem(
                    name = "5aaW57K+".decodeBase64ToStr(),
                    value = "5aaW57K+".decodeBase64ToStr(),
                ),
                MediaCatalogOptionItem(
                    name = "6a2U54mp5aiY".decodeBase64ToStr(),
                    value = "6a2U54mp5aiY".decodeBase64ToStr(),
                ),
                MediaCatalogOptionItem(
                    name = "5YW95aiY".decodeBase64ToStr(),
                    value = "5YW95aiY".decodeBase64ToStr(),
                ),
                MediaCatalogOptionItem(
                    name = "56Kn5rGg".decodeBase64ToStr(),
                    value = "56Kn5rGg".decodeBase64ToStr(),
                ),
                MediaCatalogOptionItem(
                    name = "55e05aWz".decodeBase64ToStr(),
                    value = "55e05aWz".decodeBase64ToStr(),
                ),
                MediaCatalogOptionItem(
                    name = "6JCd6I6J".decodeBase64ToStr(),
                    value = "6JCd6I6J".decodeBase64ToStr(),
                ),
                MediaCatalogOptionItem(
                    name = "5bm85aWz".decodeBase64ToStr(),
                    value = "5bm85aWz".decodeBase64ToStr(),
                ),
                MediaCatalogOptionItem(
                    name = "6ZuM5bCP6ay8".decodeBase64ToStr(),
                    value = "6ZuM5bCP6ay8".decodeBase64ToStr(),
                ),
                MediaCatalogOptionItem(
                    name = "5LiN6Imv5bCR5aWz".decodeBase64ToStr(),
                    value = "5LiN6Imv5bCR5aWz".decodeBase64ToStr(),
                ),
                MediaCatalogOptionItem(
                    name = "5YKy5aiH".decodeBase64ToStr(),
                    value = "5YKy5aiH".decodeBase64ToStr(),
                ),
                MediaCatalogOptionItem(
                    name = "55eF5aiH".decodeBase64ToStr(),
                    value = "55eF5aiH".decodeBase64ToStr(),
                ),
                MediaCatalogOptionItem(
                    name = "5peg5Y+j".decodeBase64ToStr(),
                    value = "5peg5Y+j".decodeBase64ToStr(),
                ),
                MediaCatalogOptionItem(
                    name = "5Lyq5aiY".decodeBase64ToStr(),
                    value = "5Lyq5aiY".decodeBase64ToStr(),
                ),
                MediaCatalogOptionItem(
                    name = "5om25LuW".decodeBase64ToStr(),
                    value = "5om25LuW".decodeBase64ToStr(),
                ),
                MediaCatalogOptionItem(
                    name = "55+t5Y+R".decodeBase64ToStr(),
                    value = "55+t5Y+R".decodeBase64ToStr(),
                ),
                MediaCatalogOptionItem(
                    name = "6ams5bC+".decodeBase64ToStr(),
                    value = "6ams5bC+".decodeBase64ToStr(),
                ),
                MediaCatalogOptionItem(
                    name = "5Y+M6ams5bC+".decodeBase64ToStr(),
                    value = "5Y+M6ams5bC+".decodeBase64ToStr(),
                ),
                MediaCatalogOptionItem(
                    name = "5beo5Lmz".decodeBase64ToStr(),
                    value = "5beo5Lmz".decodeBase64ToStr(),
                ),
                MediaCatalogOptionItem(
                    name = "6LSr5Lmz".decodeBase64ToStr(),
                    value = "6LSr5Lmz".decodeBase64ToStr(),
                ),
                MediaCatalogOptionItem(
                    name = "6buR55qu6IKk".decodeBase64ToStr(),
                    value = "6buR55qu6IKk".decodeBase64ToStr(),
                ),
                MediaCatalogOptionItem(
                    name = "55y86ZWc5aiY".decodeBase64ToStr(),
                    value = "55y86ZWc5aiY".decodeBase64ToStr(),
                ),
                MediaCatalogOptionItem(
                    name = "5YW96ICz".decodeBase64ToStr(),
                    value = "5YW96ICz".decodeBase64ToStr(),
                ),
                MediaCatalogOptionItem(
                    name = "576O5Lq655ej".decodeBase64ToStr(),
                    value = "576O5Lq655ej".decodeBase64ToStr(),
                ),
                MediaCatalogOptionItem(
                    name = "6IKM6IKJ5aWz".decodeBase64ToStr(),
                    value = "6IKM6IKJ5aWz".decodeBase64ToStr(),
                ),
                MediaCatalogOptionItem(
                    name = "55m96JmO".decodeBase64ToStr(),
                    value = "55m96JmO".decodeBase64ToStr(),
                ),
                MediaCatalogOptionItem(
                    name = "6Zi05q+b".decodeBase64ToStr(),
                    value = "6Zi05q+b".decodeBase64ToStr(),
                ),
                MediaCatalogOptionItem(
                    name = "6IWL5q+b".decodeBase64ToStr(),
                    value = "6IWL5q+b".decodeBase64ToStr(),
                ),
                MediaCatalogOptionItem(
                    name = "5aSn5bGM".decodeBase64ToStr(),
                    value = "5aSn5bGM".decodeBase64ToStr(),
                ),
                MediaCatalogOptionItem(
                    name = "5rC05omL5pyN".decodeBase64ToStr(),
                    value = "5rC05omL5pyN".decodeBase64ToStr(),
                ),
                MediaCatalogOptionItem(
                    name = "5L2T5pON5pyN".decodeBase64ToStr(),
                    value = "5L2T5pON5pyN".decodeBase64ToStr(),
                ),
                MediaCatalogOptionItem(
                    name = "5rOz6KOF".decodeBase64ToStr(),
                    value = "5rOz6KOF".decodeBase64ToStr(),
                ),
                MediaCatalogOptionItem(
                    name = "5q+U5Z+65bC8".decodeBase64ToStr(),
                    value = "5q+U5Z+65bC8".decodeBase64ToStr(),
                ),
                MediaCatalogOptionItem(
                    name = "5ZKM5pyN".decodeBase64ToStr(),
                    value = "5ZKM5pyN".decodeBase64ToStr(),
                ),
                MediaCatalogOptionItem(
                    name = "5YWU5aWz6YOO".decodeBase64ToStr(),
                    value = "5YWU5aWz6YOO".decodeBase64ToStr(),
                ),
                MediaCatalogOptionItem(
                    name = "5Zu06KOZ".decodeBase64ToStr(),
                    value = "5Zu06KOZ".decodeBase64ToStr(),
                ),
                MediaCatalogOptionItem(
                    name = "5ZWm5ZWm6Zif".decodeBase64ToStr(),
                    value = "5ZWm5ZWm6Zif".decodeBase64ToStr(),
                ),
                MediaCatalogOptionItem(
                    name = "5peX6KKN".decodeBase64ToStr(),
                    value = "5peX6KKN".decodeBase64ToStr(),
                ),
                MediaCatalogOptionItem(
                    name = "5Lid6KKc".decodeBase64ToStr(),
                    value = "5Lid6KKc".decodeBase64ToStr(),
                ),
                MediaCatalogOptionItem(
                    name = "5ZCK6KKc5bim".decodeBase64ToStr(),
                    value = "5ZCK6KKc5bim".decodeBase64ToStr(),
                ),
                MediaCatalogOptionItem(
                    name = "54Ot6KOk".decodeBase64ToStr(),
                    value = "54Ot6KOk".decodeBase64ToStr(),
                ),
                MediaCatalogOptionItem(
                    name = "6L+35L2g6KOZ".decodeBase64ToStr(),
                    value = "6L+35L2g6KOZ".decodeBase64ToStr(),
                ),
                MediaCatalogOptionItem(
                    name = "5oCn5oSf5YaF6KGj".decodeBase64ToStr(),
                    value = "5oCn5oSf5YaF6KGj".decodeBase64ToStr(),
                ),
                MediaCatalogOptionItem(
                    name = "5LiB5a2X6KOk".decodeBase64ToStr(),
                    value = "5LiB5a2X6KOk".decodeBase64ToStr(),
                ),
                MediaCatalogOptionItem(
                    name = "6auY6Lef6Z6L".decodeBase64ToStr(),
                    value = "6auY6Lef6Z6L".decodeBase64ToStr(),
                ),
                MediaCatalogOptionItem(
                    name = "5rer57q5".decodeBase64ToStr(),
                    value = "5rer57q5".decodeBase64ToStr(),
                ),
                MediaCatalogOptionItem(
                    name = "57qv54ix".decodeBase64ToStr(),
                    value = "57qv54ix".decodeBase64ToStr(),
                ),
                MediaCatalogOptionItem(
                    name = "5oGL54ix5Zac5Ymn".decodeBase64ToStr(),
                    value = "5oGL54ix5Zac5Ymn".decodeBase64ToStr(),
                ),
                MediaCatalogOptionItem(
                    name = "5ZCO5a6r".decodeBase64ToStr(),
                    value = "5ZCO5a6r".decodeBase64ToStr(),
                ),
                MediaCatalogOptionItem(
                    name = "5byA5aSn6L2m".decodeBase64ToStr(),
                    value = "5byA5aSn6L2m".decodeBase64ToStr(),
                ),
                MediaCatalogOptionItem(
                    name = "5YWs5LyX5Zy65ZCI".decodeBase64ToStr(),
                    value = "5YWs5LyX5Zy65ZCI".decodeBase64ToStr(),
                ),
                MediaCatalogOptionItem(
                    name = "TlRS".decodeBase64ToStr(),
                    value = "TlRS".decodeBase64ToStr(),
                ),
                MediaCatalogOptionItem(
                    name = "57K+56We5o6n5Yi2".decodeBase64ToStr(),
                    value = "57K+56We5o6n5Yi2".decodeBase64ToStr(),
                ),
                MediaCatalogOptionItem(
                    name = "6I2v54mp".decodeBase64ToStr(),
                    value = "6I2v54mp".decodeBase64ToStr(),
                ),
                MediaCatalogOptionItem(
                    name = "55e05rGJ".decodeBase64ToStr(),
                    value = "55e05rGJ".decodeBase64ToStr(),
                ),
                MediaCatalogOptionItem(
                    name = "6Zi/5Zi/6aKc".decodeBase64ToStr(),
                    value = "6Zi/5Zi/6aKc".decodeBase64ToStr(),
                ),
                MediaCatalogOptionItem(
                    name = "57K+56We5bSp5rqD".decodeBase64ToStr(),
                    value = "57K+56We5bSp5rqD".decodeBase64ToStr(),
                ),
                MediaCatalogOptionItem(
                    name = "54yO5aWH".decodeBase64ToStr(),
                    value = "54yO5aWH".decodeBase64ToStr(),
                ),
                MediaCatalogOptionItem(
                    name = "QkRTTQ==".decodeBase64ToStr(),
                    value = "QkRTTQ==".decodeBase64ToStr(),
                ),
                MediaCatalogOptionItem(
                    name = "5o2G57uR".decodeBase64ToStr(),
                    value = "5o2G57uR".decodeBase64ToStr(),
                ),
                MediaCatalogOptionItem(
                    name = "55y8572p".decodeBase64ToStr(),
                    value = "55y8572p".decodeBase64ToStr(),
                ),
                MediaCatalogOptionItem(
                    name = "6aG55ZyI".decodeBase64ToStr(),
                    value = "6aG55ZyI".decodeBase64ToStr(),
                ),
                MediaCatalogOptionItem(
                    name = "6LCD5pWZ".decodeBase64ToStr(),
                    value = "6LCD5pWZ".decodeBase64ToStr(),
                ),
                MediaCatalogOptionItem(
                    name = "5byC54mp5o+S5YWl".decodeBase64ToStr(),
                    value = "5byC54mp5o+S5YWl".decodeBase64ToStr(),
                ),
                MediaCatalogOptionItem(
                    name = "6IKJ5L6/5Zmo".decodeBase64ToStr(),
                    value = "6IKJ5L6/5Zmo".decodeBase64ToStr(),
                ),
                MediaCatalogOptionItem(
                    name = "6IOD5Ye4".decodeBase64ToStr(),
                    value = "6IOD5Ye4".decodeBase64ToStr(),
                ),
                MediaCatalogOptionItem(
                    name = "5by65Yi2".decodeBase64ToStr(),
                    value = "5by65Yi2".decodeBase64ToStr(),
                ),
                MediaCatalogOptionItem(
                    name = "6L2u5aW4".decodeBase64ToStr(),
                    value = "6L2u5aW4".decodeBase64ToStr(),
                ),
                MediaCatalogOptionItem(
                    name = "5YeM6L6x".decodeBase64ToStr(),
                    value = "5YeM6L6x".decodeBase64ToStr(),
                ),
                MediaCatalogOptionItem(
                    name = "5oCn5pq05Yqb".decodeBase64ToStr(),
                    value = "5oCn5pq05Yqb".decodeBase64ToStr(),
                ),
                MediaCatalogOptionItem(
                    name = "6YCG5by65Yi2".decodeBase64ToStr(),
                    value = "6YCG5by65Yi2".decodeBase64ToStr(),
                ),
                MediaCatalogOptionItem(
                    name = "5aWz546L5qC3".decodeBase64ToStr(),
                    value = "5aWz546L5qC3".decodeBase64ToStr(),
                ),
                MediaCatalogOptionItem(
                    name = "5q+N5aWz5Li8".decodeBase64ToStr(),
                    value = "5q+N5aWz5Li8".decodeBase64ToStr(),
                ),
                MediaCatalogOptionItem(
                    name = "5aeQ5aa55Li8".decodeBase64ToStr(),
                    value = "5aeQ5aa55Li8".decodeBase64ToStr(),
                ),
                MediaCatalogOptionItem(
                    name = "5Ye66L2o".decodeBase64ToStr(),
                    value = "5Ye66L2o".decodeBase64ToStr(),
                ),
                MediaCatalogOptionItem(
                    name = "5pGE5b2x".decodeBase64ToStr(),
                    value = "5pGE5b2x".decodeBase64ToStr(),
                ),
                MediaCatalogOptionItem(
                    name = "552h55yg5aW4".decodeBase64ToStr(),
                    value = "552h55yg5aW4".decodeBase64ToStr(),
                ),
                MediaCatalogOptionItem(
                    name = "5py65qKw5aW4".decodeBase64ToStr(),
                    value = "5py65qKw5aW4".decodeBase64ToStr(),
                ),
                MediaCatalogOptionItem(
                    name = "5oCn6L2s5o2i".decodeBase64ToStr(),
                    value = "5oCn6L2s5o2i".decodeBase64ToStr(),
                ),
                MediaCatalogOptionItem(
                    name = "55m+5ZCI".decodeBase64ToStr(),
                    value = "55m+5ZCI".decodeBase64ToStr(),
                ),
                MediaCatalogOptionItem(
                    name = "6IC9576O".decodeBase64ToStr(),
                    value = "6IC9576O".decodeBase64ToStr(),
                ),
                MediaCatalogOptionItem(
                    name = "5byC5LiW55WM".decodeBase64ToStr(),
                    value = "5byC5LiW55WM".decodeBase64ToStr(),
                ),
                MediaCatalogOptionItem(
                    name = "5oCq5YW9".decodeBase64ToStr(),
                    value = "5oCq5YW9".decodeBase64ToStr(),
                ),
                MediaCatalogOptionItem(
                    name = "5LiW55WM5pyr5pel".decodeBase64ToStr(),
                    value = "5LiW55WM5pyr5pel".decodeBase64ToStr(),
                ),
                MediaCatalogOptionItem(
                    name = "5omL5Lqk".decodeBase64ToStr(),
                    value = "5omL5Lqk".decodeBase64ToStr(),
                ),
                MediaCatalogOptionItem(
                    name = "5oyH5Lqk".decodeBase64ToStr(),
                    value = "5oyH5Lqk".decodeBase64ToStr(),
                ),
                MediaCatalogOptionItem(
                    name = "5Lmz5Lqk".decodeBase64ToStr(),
                    value = "5Lmz5Lqk".decodeBase64ToStr(),
                ),
                MediaCatalogOptionItem(
                    name = "6IKb5Lqk".decodeBase64ToStr(),
                    value = "6IKb5Lqk".decodeBase64ToStr(),
                ),
                MediaCatalogOptionItem(
                    name = "6ISa5Lqk".decodeBase64ToStr(),
                    value = "6ISa5Lqk".decodeBase64ToStr(),
                ),
                MediaCatalogOptionItem(
                    name = "5ouz5Lqk".decodeBase64ToStr(),
                    value = "5ouz5Lqk".decodeBase64ToStr(),
                ),
                MediaCatalogOptionItem(
                    name = "M1A=".decodeBase64ToStr(),
                    value = "M1A=".decodeBase64ToStr(),
                ),
                MediaCatalogOptionItem(
                    name = "576k5Lqk".decodeBase64ToStr(),
                    value = "576k5Lqk".decodeBase64ToStr(),
                ),
                MediaCatalogOptionItem(
                    name = "5Y+j5Lqk".decodeBase64ToStr(),
                    value = "5Y+j5Lqk".decodeBase64ToStr(),
                ),
                MediaCatalogOptionItem(
                    name = "5Y+j54iG".decodeBase64ToStr(),
                    value = "5Y+j54iG".decodeBase64ToStr(),
                ),
                MediaCatalogOptionItem(
                    name = "5ZCe57K+".decodeBase64ToStr(),
                    value = "5ZCe57K+".decodeBase64ToStr(),
                ),
                MediaCatalogOptionItem(
                    name = "6IiU6JuL6JuL".decodeBase64ToStr(),
                    value = "6IiU6JuL6JuL".decodeBase64ToStr(),
                ),
                MediaCatalogOptionItem(
                    name = "6IiU56m0".decodeBase64ToStr(),
                    value = "6IiU56m0".decodeBase64ToStr(),
                ),
                MediaCatalogOptionItem(
                    name = "Njk=".decodeBase64ToStr(),
                    value = "Njk=".decodeBase64ToStr(),
                ),
                MediaCatalogOptionItem(
                    name = "6Ieq5oWw".decodeBase64ToStr(),
                    value = "6Ieq5oWw".decodeBase64ToStr(),
                ),
                MediaCatalogOptionItem(
                    name = "6IWL5Lqk".decodeBase64ToStr(),
                    value = "6IWL5Lqk".decodeBase64ToStr(),
                ),
                MediaCatalogOptionItem(
                    name = "6IiU6IWL5LiL".decodeBase64ToStr(),
                    value = "6IiU6IWL5LiL".decodeBase64ToStr(),
                ),
                MediaCatalogOptionItem(
                    name = "5YaF5bCE".decodeBase64ToStr(),
                    value = "5YaF5bCE".decodeBase64ToStr(),
                ),
                MediaCatalogOptionItem(
                    name = "6aKc5bCE".decodeBase64ToStr(),
                    value = "6aKc5bCE".decodeBase64ToStr(),
                ),
                MediaCatalogOptionItem(
                    name = "5Y+M5rSe6b2Q5LiL".decodeBase64ToStr(),
                    value = "5Y+M5rSe6b2Q5LiL".decodeBase64ToStr(),
                ),
                MediaCatalogOptionItem(
                    name = "5oCA5a2V".decodeBase64ToStr(),
                    value = "5oCA5a2V".decodeBase64ToStr(),
                ),
                MediaCatalogOptionItem(
                    name = "5Za35aW2".decodeBase64ToStr(),
                    value = "5Za35aW2".decodeBase64ToStr(),
                ),
                MediaCatalogOptionItem(
                    name = "5pS+5bC/".decodeBase64ToStr(),
                    value = "5pS+5bC/".decodeBase64ToStr(),
                ),
                MediaCatalogOptionItem(
                    name = "5o6S5L6/".decodeBase64ToStr(),
                    value = "5o6S5L6/".decodeBase64ToStr(),
                ),
                MediaCatalogOptionItem(
                    name = "6aKc6Z2i6aqR5LmY".decodeBase64ToStr(),
                    value = "6aKc6Z2i6aqR5LmY".decodeBase64ToStr(),
                ),
                MediaCatalogOptionItem(
                    name = "6L2m6ZyH".decodeBase64ToStr(),
                    value = "6L2m6ZyH".decodeBase64ToStr(),
                ),
                MediaCatalogOptionItem(
                    name = "5oCn546p5YW3".decodeBase64ToStr(),
                    value = "5oCn546p5YW3".decodeBase64ToStr(),
                ),
                MediaCatalogOptionItem(
                    name = "5q+S6b6Z6ZK7".decodeBase64ToStr(),
                    value = "5q+S6b6Z6ZK7".decodeBase64ToStr(),
                ),
                MediaCatalogOptionItem(
                    name = "6Kem5omL".decodeBase64ToStr(),
                    value = "6Kem5omL".decodeBase64ToStr(),
                ),
                MediaCatalogOptionItem(
                    name = "6aKI5omL5p63".decodeBase64ToStr(),
                    value = "6aKI5omL5p63".decodeBase64ToStr(),
                ),
                MediaCatalogOptionItem(
                    name = "552A6KGj".decodeBase64ToStr(),
                    value = "552A6KGj".decodeBase64ToStr(),
                ),
                MediaCatalogOptionItem(
                    name = "UXVlZW4=".decodeBase64ToStr(),
                    value = "UXVlZW4=".decodeBase64ToStr(),
                ),
                MediaCatalogOptionItem(
                    name = "QmVl".decodeBase64ToStr(),
                    value = "QmVl".decodeBase64ToStr(),
                ),
                MediaCatalogOptionItem(
                    name = "44GC44KT44Gm44GN44Gs44GZ44Gj".decodeBase64ToStr(),
                    value = "44GC44KT44Gm44GN44Gs44GZ44Gj".decodeBase64ToStr(),
                ),
                MediaCatalogOptionItem(
                    name = "44Gw44Gr44GD44GG44GJ44Cc44GL44Cc".decodeBase64ToStr(),
                    value = "44Gw44Gr44GD44GG44GJ44Cc44GL44Cc".decodeBase64ToStr(),
                ),
                MediaCatalogOptionItem(
                    name = "44Oh44Oq44O844O744K444Kn44O844Oz".decodeBase64ToStr(),
                    value = "44Oh44Oq44O844O744K444Kn44O844Oz".decodeBase64ToStr(),
                ),
                MediaCatalogOptionItem(
                    name = "44K144O844Kv44Or44OI44Oq44OT44Ol44O844OI".decodeBase64ToStr(),
                    value = "44K144O844Kv44Or44OI44Oq44OT44Ol44O844OI".decodeBase64ToStr(),
                ),
                MediaCatalogOptionItem(
                    name = "TWlsa3k=".decodeBase64ToStr(),
                    value = "TWlsa3k=".decodeBase64ToStr(),
                ),
                MediaCatalogOptionItem(
                    name = "44Of44Or44Kv44K744O844Kt".decodeBase64ToStr(),
                    value = "44Of44Or44Kv44K744O844Kt".decodeBase64ToStr(),
                ),
                MediaCatalogOptionItem(
                    name = "RURHRQ==".decodeBase64ToStr(),
                    value = "RURHRQ==".decodeBase64ToStr(),
                ),
                MediaCatalogOptionItem(
                    name = "V0hJVEU=".decodeBase64ToStr(),
                    value = "V0hJVEU=".decodeBase64ToStr(),
                ),
                MediaCatalogOptionItem(
                    name = "QkVBUg==".decodeBase64ToStr(),
                    value = "QkVBUg==".decodeBase64ToStr(),
                ),
                MediaCatalogOptionItem(
                    name = "UG9STw==".decodeBase64ToStr(),
                    value = "UG9STw==".decodeBase64ToStr(),
                ),
                MediaCatalogOptionItem(
                    name = "Q29sbGFib3JhdGlvbg==".decodeBase64ToStr(),
                    value = "Q29sbGFib3JhdGlvbg==".decodeBase64ToStr(),
                ),
                MediaCatalogOptionItem(
                    name = "V29ya3M=".decodeBase64ToStr(),
                    value = "V29ya3M=".decodeBase64ToStr(),
                ),
                MediaCatalogOptionItem(
                    name = "6Yi05pyo44G/44KJ5LmD".decodeBase64ToStr(),
                    value = "6Yi05pyo44G/44KJ5LmD".decodeBase64ToStr(),
                ),
                MediaCatalogOptionItem(
                    name = "UGFzaG1pbmFB".decodeBase64ToStr(),
                    value = "UGFzaG1pbmFB".decodeBase64ToStr(),
                ),
                MediaCatalogOptionItem(
                    name = "Y2hpcHBhaQ==".decodeBase64ToStr(),
                    value = "Y2hpcHBhaQ==".decodeBase64ToStr(),
                ),
                MediaCatalogOptionItem(
                    name = "6a2U5Lq6".decodeBase64ToStr(),
                    value = "6a2U5Lq6".decodeBase64ToStr(),
                ),
                MediaCatalogOptionItem(
                    name = "Qk9PVExFRw==".decodeBase64ToStr(),
                    value = "Qk9PVExFRw==".decodeBase64ToStr(),
                ),
                MediaCatalogOptionItem(
                    name = "Wkla".decodeBase64ToStr(),
                    value = "Wkla".decodeBase64ToStr(),
                ),
                MediaCatalogOptionItem(
                    name = "UElYWQ==".decodeBase64ToStr(),
                    value = "UElYWQ==".decodeBase64ToStr(),
                ),
                MediaCatalogOptionItem(
                    name = "R1JFRU4=".decodeBase64ToStr(),
                    value = "R1JFRU4=".decodeBase64ToStr(),
                ),
                MediaCatalogOptionItem(
                    name = "QlVOTlk=".decodeBase64ToStr(),
                    value = "QlVOTlk=".decodeBase64ToStr(),
                ),
                MediaCatalogOptionItem(
                    name = "bnVy".decodeBase64ToStr(),
                    value = "bnVy".decodeBase64ToStr(),
                ),
                MediaCatalogOptionItem(
                    name = "R09MRA==".decodeBase64ToStr(),
                    value = "R09MRA==".decodeBase64ToStr(),
                ),
                MediaCatalogOptionItem(
                    name = "QkVBUg==".decodeBase64ToStr(),
                    value = "QkVBUg==".decodeBase64ToStr(),
                ),
                MediaCatalogOptionItem(
                    name = "44KP44KL44GN44KF44Cc44KM".decodeBase64ToStr(),
                    value = "44KP44KL44GN44KF44Cc44KM".decodeBase64ToStr(),
                ),
                MediaCatalogOptionItem(
                    name = "44OG44Kj44O844Os44OD44Kv44K5".decodeBase64ToStr(),
                    value = "44OG44Kj44O844Os44OD44Kv44K5".decodeBase64ToStr(),
                ),
                MediaCatalogOptionItem(
                    name = "5aaE5oOz5bCC56eR".decodeBase64ToStr(),
                    value = "5aaE5oOz5bCC56eR".decodeBase64ToStr(),
                ),
                MediaCatalogOptionItem(
                    name = "Qk9NQiE=".decodeBase64ToStr(),
                    value = "Qk9NQiE=".decodeBase64ToStr(),
                ),
                MediaCatalogOptionItem(
                    name = "Q1VURSE=".decodeBase64ToStr(),
                    value = "Q1VURSE=".decodeBase64ToStr(),
                ),
                MediaCatalogOptionItem(
                    name = "Qk9NQiE=".decodeBase64ToStr(),
                    value = "Qk9NQiE=".decodeBase64ToStr(),
                ),
                MediaCatalogOptionItem(
                    name = "44GT44Gj44Go44KT44Gp772e44KL".decodeBase64ToStr(),
                    value = "44GT44Gj44Go44KT44Gp772e44KL".decodeBase64ToStr(),
                ),
                MediaCatalogOptionItem(
                    name = "44OU44Oz44Kv44OR44Kk44OK44OD44OX44Or".decodeBase64ToStr(),
                    value = "44OU44Oz44Kv44OR44Kk44OK44OD44OX44Or".decodeBase64ToStr(),
                ),
            ),
        ),
        MediaCatalogOption(
            name = "地区",
            value = "region",
            items = listOf(
                MediaCatalogOptionItem(
                    name = "全部",
                    value = "",
                    defaultChecked = true,
                ),
                MediaCatalogOptionItem(
                    name = "大陆",
                    value = "中国大陆",
                ),
                MediaCatalogOptionItem(
                    name = "香港",
                    value = "香港",
                ),
                MediaCatalogOptionItem(
                    name = "台湾",
                    value = "台湾",
                ),
                MediaCatalogOptionItem(
                    name = "美国",
                    value = "美国",
                ),
                MediaCatalogOptionItem(
                    name = "法国",
                    value = "法国",
                ),
                MediaCatalogOptionItem(
                    name = "英国",
                    value = "英国",
                ),
                MediaCatalogOptionItem(
                    name = "日本",
                    value = "日本",
                ),
                MediaCatalogOptionItem(
                    name = "日本",
                    value = "日本",
                ),
                MediaCatalogOptionItem(
                    name = "韩国",
                    value = "韩国",
                ),
                MediaCatalogOptionItem(
                    name = "德国",
                    value = "德国",
                ),
                MediaCatalogOptionItem(
                    name = "泰国",
                    value = "泰国",
                ),
                MediaCatalogOptionItem(
                    name = "印度",
                    value = "印度",
                ),
                MediaCatalogOptionItem(
                    name = "意大利",
                    value = "意大利",
                ),
                MediaCatalogOptionItem(
                    name = "西班牙",
                    value = "西班牙",
                ),
            )
        ),
        MediaCatalogOption(
            name = "年份",
            value = "year",
            required = true,
            items = buildList {
                add(
                    MediaCatalogOptionItem(
                        name = "全部",
                        value = "",
                        defaultChecked = true,
                    )
                )
                val currentYear = Calendar.getInstance().get(Calendar.YEAR)
                for (year in currentYear downTo 1982) {
                    MediaCatalogOptionItem(
                        name = "$year",
                        value = "$year",
                    )
                }
            }
        ),
        MediaCatalogOption(
            name = "语言",
            value = "language",
            required = true,
            items = listOf(
                MediaCatalogOptionItem(
                    name = "全部",
                    value = "",
                    defaultChecked = true
                ),
                MediaCatalogOptionItem(
                    name = "国语",
                    value = "国语",
                ),
                MediaCatalogOptionItem(
                    name = "英语",
                    value = "英语",
                ),
                MediaCatalogOptionItem(
                    name = "粤语",
                    value = "粤语",
                ),
                MediaCatalogOptionItem(
                    name = "闽南语",
                    value = "闽南语",
                ),
                MediaCatalogOptionItem(
                    name = "韩语",
                    value = "韩语",
                ),
                MediaCatalogOptionItem(
                    name = "日语",
                    value = "日语",
                ),
                MediaCatalogOptionItem(
                    name = "法语",
                    value = "法语",
                ),
                MediaCatalogOptionItem(
                    name = "德语",
                    value = "德语",
                ),
                MediaCatalogOptionItem(
                    name = "其它",
                    value = "其它",
                ),
            ),
        ),
        MediaCatalogOption(
            name = "排序",
            value = "order",
            required = true,
            items = listOf(
                MediaCatalogOptionItem(
                    name = "时间",
                    value = "time",
                    defaultChecked = true
                ),
                MediaCatalogOptionItem(
                    name = "人气",
                    value = "hits",
                ),
                MediaCatalogOptionItem(
                    name = "评分",
                    value = "score",
                ),
            ),
        ),
    )
}