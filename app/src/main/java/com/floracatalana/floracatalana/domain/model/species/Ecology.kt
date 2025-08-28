package com.floracatalana.floracatalana.domain.model.species


import androidx.annotation.Keep

@Keep
data class Ecology(
    val altitude: Altitude = Altitude(),
    val frequency: String? = null,
    val habitat: String? = "",
    val phytosociology: String? = null,
    val territory: Territory = Territory()
)