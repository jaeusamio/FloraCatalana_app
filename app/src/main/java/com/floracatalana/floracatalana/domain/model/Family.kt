package com.floracatalana.floracatalana.domain.model


import androidx.annotation.Keep

@Keep
data class Family(
    val code: String = "",
    val nameCat: String = "",
    val nameLatin: String = "",
    val nodeId: String = "",
    val rank: String = "",
    val url: String = "",
)