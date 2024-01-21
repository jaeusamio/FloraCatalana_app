package com.floracatalana.floracatalana.domain.model.species


import androidx.annotation.Keep
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Keep
@Serializable
data class Ecology(
    @SerialName("altitude")
    val altitude: Altitude = Altitude(),
    @SerialName("frequency")
    val frequency: String? = null,
    @SerialName("habitat")
    val habitat: String? = "",
    @SerialName("phytosociology")
    val phytosociology: String? = "",
    @SerialName("territory")
    val territory: Territory = Territory()
)