package com.floracatalana.floracatalana.domain.model.species


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import androidx.annotation.Keep

@Keep
@Serializable
data class Size(
    @SerialName("max_size")
    val maxSize: String? = null,
    @SerialName("min_size")
    val minSize: String? = null
)