package com.floracatalana.floracatalana.domain.model


import androidx.annotation.Keep

@Keep
data class Genus(
    val code: String = "",
    val shortFamily: ShortTaxon = ShortTaxon(),
    val nameCat: String = "",
    val nameLatin: String = "",
    val nodeId: String = "",
    val rank: String = "",
    val url: String = "",
)