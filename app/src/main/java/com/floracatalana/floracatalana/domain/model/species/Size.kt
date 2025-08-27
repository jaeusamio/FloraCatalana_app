package com.floracatalana.floracatalana.domain.model.species


import androidx.annotation.Keep

@Keep
data class Size(
    val maxSize: String? = null,
    val minSize: String? = null
)