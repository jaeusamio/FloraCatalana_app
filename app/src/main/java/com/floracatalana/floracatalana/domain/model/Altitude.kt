package com.floracatalana.floracatalana.domain.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import androidx.annotation.Keep

@Keep
@Serializable
data class Altitude(
    @SerialName("altitud_maxima")
    val altitudMaxima: Int = 0,
    @SerialName("altitud_minima")
    val altitudMinima: Int = 0,
    @SerialName("altitud_minima_inferior")
    val altitudMinimaInferior: Int? = null,
    @SerialName("altitud_maxima_superior")
    val altitudMaximaSuperior: Int? = null
)