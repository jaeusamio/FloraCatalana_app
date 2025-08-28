package com.floracatalana.floracatalana.domain.model.species


import androidx.annotation.Keep

@Keep
data class Altitude(
    val altitudMaxima: Int? = null,
    val altitudMinima: Int? = null,
    val altitudMinimaInferior: Int? = null,
    val altitudMaximaSuperior: Int? = null
)