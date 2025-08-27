package com.floracatalana.floracatalana.domain.model.species


import androidx.annotation.Keep

@Keep
data class Altitude(
    val altitudMaxima: Int = 0,
    val altitudMinima: Int = 0,
    val altitudMinimaInferior: Int? = null,
    val altitudMaximaSuperior: Int? = null
)