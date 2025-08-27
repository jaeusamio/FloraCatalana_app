package com.floracatalana.floracatalana.domain.model.species


import androidx.annotation.Keep
import com.floracatalana.floracatalana.domain.model.ShortTaxon

@Keep
data class Species(
    val categoryImages: List<CategoryImage> = listOf(),
    val code: String = "",
    val distribution: String? = null,
    val description: Description? = Description(),
    val ecology: Ecology? = Ecology(),
    val flowering: Flowering? = Flowering(),
    val images: List<Image> = listOf(),
    val nameCat: String? = null,
    val nameLatin: String = "",
    val nodeId: String = "",
    val nomenclature: Nomenclature? = Nomenclature(),
    val rank: String = "",
    val shortFamily: ShortTaxon = ShortTaxon(),
    val shortGenus: ShortTaxon = ShortTaxon(),
    val subspecies: List<Species> = listOf(),
    val taxonomy: Taxonomy? = Taxonomy(),
    val url: String = ""
)