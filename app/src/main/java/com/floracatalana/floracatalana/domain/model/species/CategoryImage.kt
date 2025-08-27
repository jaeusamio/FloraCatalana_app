package com.floracatalana.floracatalana.domain.model.species


import androidx.annotation.Keep

@Keep
data class CategoryImage(
    val label: String = "",
    val url: String? = ""
)