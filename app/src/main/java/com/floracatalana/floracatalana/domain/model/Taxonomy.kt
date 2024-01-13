package com.floracatalana.floracatalana.domain.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import androidx.annotation.Keep

@Keep
@Serializable
data class Taxonomy(
    @SerialName("apg_family")
    val apgFamily: String? = "",
    @SerialName("apg_genus")
    val apgGenus: String? = "",
    @SerialName("apg_order")
    val apgOrder: String? = "",
    @SerialName("tpl_name")
    val tplName: String? = ""
)