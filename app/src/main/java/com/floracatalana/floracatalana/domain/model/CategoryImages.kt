package com.floracatalana.floracatalana.domain.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import androidx.annotation.Keep

@Keep
@Serializable
data class CategoryImages(
    @SerialName("ALTRES")
    val altres: String? = null,
    @SerialName("FLOR/INFLORESC.")
    val flor_inflorescencia: String? = null,
    @SerialName("FRUIT/LLAVOR")
    val fruit_llavor: String? = null,
    @SerialName("FULLES")
    val fulles: String? = null,
    @SerialName("HÀBITAT")
    val habitat: String? = null,
    @SerialName("PORT")
    val port: String? = null,
    @SerialName("SUBTERRANI")
    val subterrani: String? = null,
    @SerialName("TIJA/TRONC")
    val tija_tronc: String? = null
)