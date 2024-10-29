package com.muedsa.tvbox.an1me

import com.muedsa.tvbox.api.data.MediaCard
import com.muedsa.tvbox.api.data.MediaCardRow
import com.muedsa.tvbox.api.data.MediaCardType

fun checkMediaCardRows(rows: List<MediaCardRow>) {
    rows.forEach { checkMediaCardRow(it) }
}

fun checkMediaCardRow(row: MediaCardRow) {
    check(row.title.isNotEmpty()) { "check failed $row" }
    check(row.list.isNotEmpty()) { "check failed $row" }
    check(row.cardWidth > 0) { "check failed $row" }
    check(row.cardHeight > 0) { "check failed $row" }
    println("# ${row.title}")
    row.list.forEach {
        checkMediaCard(card = it, cardType = row.cardType)
    }
}

fun checkMediaCard(card: MediaCard, cardType: MediaCardType) {
    check(card.id.isNotEmpty()) { "check failed $card" }
    check(card.title.isNotEmpty()) { "check failed $card" }
    check(card.detailUrl.isNotEmpty()) { "check failed $card" }
    if (cardType != MediaCardType.NOT_IMAGE) {
        check(card.coverImageUrl.isNotEmpty())
        println(">${card.title} ${card.detailUrl} ${card.coverImageUrl}")
    }  else {
        check(card.backgroundColor > 0)
        println(">${card.title} ${card.detailUrl}")
    }
}