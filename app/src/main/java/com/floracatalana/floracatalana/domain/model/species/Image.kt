package com.floracatalana.floracatalana.domain.model.species


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import androidx.annotation.Keep

@Keep
@Serializable
data class Image(
    @SerialName("tags")
    val tags: List<String>? = null,
    @SerialName("url")
    val url: String = ""
)