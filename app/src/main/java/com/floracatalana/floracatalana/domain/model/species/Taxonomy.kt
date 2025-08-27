package com.floracatalana.floracatalana.domain.model.species


import androidx.annotation.Keep

@Keep
data class Taxonomy(
    val apgFamily: String? = "",
    val apgGenus: String? = "",
    val apgOrder: String? = "",
    val tplName: String? = ""
)