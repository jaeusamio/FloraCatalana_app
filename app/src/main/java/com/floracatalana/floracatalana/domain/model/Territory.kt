package com.floracatalana.floracatalana.domain.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import androidx.annotation.Keep

@Keep
@Serializable
data class Territory(
    @SerialName("distribució_general")
    val distribucioGeneral: String? = null,
    @SerialName("fisiografic_catalunya")
    val fisiograficCatalunya: String? = null,
    @SerialName("zones_fitogeografiques")
    val zonesFitogeografiques: String? = null
)