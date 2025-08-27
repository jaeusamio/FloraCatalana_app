package com.floracatalana.floracatalana.domain.model.species


import androidx.annotation.Keep

@Keep
data class Image(
    val tags: List<String>? = null,
    val url: String = ""
)