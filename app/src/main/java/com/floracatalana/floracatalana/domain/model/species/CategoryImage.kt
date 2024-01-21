package com.floracatalana.floracatalana.domain.model.species


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import androidx.annotation.Keep

@Keep
@Serializable
data class CategoryImage(
    @SerialName("label")
    val label: String = "",
    @SerialName("url")
    val url: String? = ""
)