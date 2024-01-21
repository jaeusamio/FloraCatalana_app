package com.floracatalana.floracatalana.domain.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import androidx.annotation.Keep

@Keep
@Serializable
data class ShortTaxon(
    @SerialName("code")
    val code: String = "",
    @SerialName("name")
    val name: String = ""
)