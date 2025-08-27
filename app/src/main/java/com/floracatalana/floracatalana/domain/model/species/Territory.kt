package com.floracatalana.floracatalana.domain.model.species


import androidx.annotation.Keep

@Keep
data class Territory(
    val distribucioGeneral: String? = null,
    val fisiograficCatalunya: String? = null,
    val zonesFitogeografiques: String? = null
)