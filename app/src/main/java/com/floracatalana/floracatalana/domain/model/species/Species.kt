package com.floracatalana.floracatalana.domain.model.species


import androidx.annotation.Keep
import com.floracatalana.floracatalana.domain.model.ShortTaxon
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

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