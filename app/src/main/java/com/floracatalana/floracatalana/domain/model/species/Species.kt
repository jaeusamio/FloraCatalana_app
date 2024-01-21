package com.floracatalana.floracatalana.domain.model.species


import androidx.annotation.Keep
import com.floracatalana.floracatalana.domain.model.ShortTaxon
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

//@Keep
//@Serializable
//data class Species(
//    @SerialName("altitude")
//    val altitude: Altitude = Altitude(),
//    @SerialName("category_images")
//    val categoryImages: List<CategoryImage> = listOf(),
//    @SerialName("code")
//    val code: String = "",
//    @SerialName("family_code")
//    val familyCode: String = "",
//    @SerialName("flowering")
//    val flowering: Flowering? = Flowering(),
//    @SerialName("frequency")
//    val frequency: String? = null,
//    @SerialName("genus_code")
//    val genusCode: String = "",
//    @SerialName("habitat")
//    val habitat: String? = "",
//    @SerialName("images")
//    val images: List<Image> = listOf(),
//    @SerialName("life_form")
//    val lifeForm: String? = "",
//    @SerialName("name_cat")
//    val nameCat: String? = null,
//    @SerialName("name_latin")
//    val nameLatin: String = "",
//    @SerialName("node_id")
//    val nodeId: String = "",
//    @SerialName("nomenclature")
//    val nomenclature: Nomenclature = Nomenclature(),
//    @SerialName("phytosociology")
//    val phytosociology: String? = "",
//    @SerialName("rank")
//    val rank: String = "",
//    @SerialName("size")
//    val size: Size = Size(),
//    @SerialName("subspecies")
//    val subspecies: List<Subspecies> = listOf(),
//    @SerialName("taxonomy")
//    val taxonomy: Taxonomy? = Taxonomy(),
//    @SerialName("territory")
//    val territory: Territory = Territory(),
//    @SerialName("url")
//    val url: String = "",
//    @SerialName("family_name_cat")
//    val familyNameCat: String = ""
//)

@Keep
@Serializable
data class Species(
    @SerialName("category_images")
    val categoryImages: List<CategoryImage> = listOf(),
    @SerialName("code")
    val code: String = "",
    @SerialName("distribution")
    val distribution: String? = null,
    @SerialName("description")
    val description: Description? = Description(),
    @SerialName("ecology")
    val ecology: Ecology? = Ecology(),
    @SerialName("flowering")
    val flowering: Flowering? = Flowering(),
    @SerialName("images")
    val images: List<Image> = listOf(),
    @SerialName("name_cat")
    val nameCat: String? = null,
    @SerialName("name_latin")
    val nameLatin: String = "",
    @SerialName("node_id")
    val nodeId: String = "",
    @SerialName("nomenclature")
    val nomenclature: Nomenclature? = Nomenclature(),
    @SerialName("rank")
    val rank: String = "",
    @SerialName("short_family")
    val shortFamily: ShortTaxon = ShortTaxon(),
    @SerialName("short_genus")
    val shortGenus: ShortTaxon = ShortTaxon(),
    @SerialName("subspecies")
    val subspecies: List<Species> = listOf(),
    @SerialName("taxonomy")
    val taxonomy: Taxonomy? = Taxonomy(),
    @SerialName("url")
    val url: String = ""
)