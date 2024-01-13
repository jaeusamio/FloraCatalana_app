package com.floracatalana.floracatalana.domain.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import androidx.annotation.Keep

@Keep
@Serializable
data class Subspecies(
    @SerialName("altitude")
    val altitude: Altitude = Altitude(),
    @SerialName("category_images")
    val categoryImages: CategoryImages = CategoryImages(),
    @SerialName("code")
    val code: String = "",
    @SerialName("family_code")
    val familyCode: String = "",
    @SerialName("flowering")
    val flowering: Flowering? = Flowering(),
    @SerialName("frequency")
    val frequency: String? = "",
    @SerialName("genus_code")
    val genusCode: String = "",
    @SerialName("habitat")
    val habitat: String? = "",
    @SerialName("images")
    val images: List<Image> = listOf(),
    @SerialName("life_form")
    val lifeForm: String? = "",
    @SerialName("name_cat")
    val nameCat: String? = null,
    @SerialName("name_latin")
    val nameLatin: String = "",
    @SerialName("node_id")
    val nodeId: String = "",
    @SerialName("nomenclature")
    val nomenclature: Nomenclature = Nomenclature(),
    @SerialName("phytosociology")
    val phytosociology: String? = null,
    @SerialName("rank")
    val rank: String = "",
    @SerialName("size")
    val size: Size = Size(),
    @SerialName("taxonomy")
    val taxonomy: Taxonomy? = Taxonomy(),
    @SerialName("territory")
    val territory: Territory = Territory(),
    @SerialName("url")
    val url: String = ""
)