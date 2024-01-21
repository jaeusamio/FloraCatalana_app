package com.floracatalana.floracatalana.domain.model.species


import androidx.annotation.Keep
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Keep
@Serializable
data class Description(
    @SerialName("life_form")
    val lifeForm: String? = "",
    @SerialName("size")
    val size: Size = Size()
)