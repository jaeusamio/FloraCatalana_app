package com.floracatalana.floracatalana.domain.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import androidx.annotation.Keep

@Keep
@Serializable
data class Flowering(
    @SerialName("ABRIL")
    val april: Boolean = false,
    @SerialName("AGOST")
    val august: Boolean = false,
    @SerialName("DESEMBRE")
    val december: Boolean = false,
    @SerialName("FEBRER")
    val february: Boolean = false,
    @SerialName("GENER")
    val january: Boolean = false,
    @SerialName("JULIOL")
    val july: Boolean = false,
    @SerialName("JUNY")
    val june: Boolean = false,
    @SerialName("MAIG")
    val may: Boolean = false,
    @SerialName("MARÇ")
    val march: Boolean = false,
    @SerialName("NOVEMBRE")
    val november: Boolean = false,
    @SerialName("OCTUBRE")
    val october: Boolean = false,
    @SerialName("SETEMBRE")
    val september: Boolean = false
)