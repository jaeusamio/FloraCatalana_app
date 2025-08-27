package com.floracatalana.floracatalana.domain.model


import androidx.annotation.Keep

@Keep
data class Family(
    val code: String = "",
    val nGenera: Int = 0,
    val nameCat: String = "",
    val nameLatin: String = "",
    val nodeId: String = "",
    val rank: String = "",
    val url: String = "",
    val nSpecies: Int = 0,
    val genera: List<ShortTaxon> = listOf()
)