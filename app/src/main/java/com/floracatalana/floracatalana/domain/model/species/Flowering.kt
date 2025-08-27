package com.floracatalana.floracatalana.domain.model.species


import androidx.annotation.Keep

@Keep
data class Flowering(
    val january: Boolean = false,
    val february: Boolean = false,
    val march: Boolean = false,
    val april: Boolean = false,
    val may: Boolean = false,
    val june: Boolean = false,
    val july: Boolean = false,
    val august: Boolean = false,
    val september: Boolean = false,
    val october: Boolean = false,
    val november: Boolean = false,
    val december: Boolean = false
)