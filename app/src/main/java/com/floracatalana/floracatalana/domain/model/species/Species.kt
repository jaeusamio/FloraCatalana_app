package com.floracatalana.floracatalana.domain.model.species


import androidx.annotation.Keep
import com.floracatalana.floracatalana.domain.model.ShortTaxon
import com.floracatalana.floracatalana.domain.model.Taxon
import com.floracatalana.floracatalana.domain.model.TaxonRank

@Keep
data class Species(
    override val code: String = "",
    override val nodeId: String = "",
    override val rank: TaxonRank = TaxonRank.SPECIES,
    override val nameLatin: String = "",
    override val nameCat: String? = null,
    override val url: String = "",
    val categoryImages: List<CategoryImage> = listOf(),
    val distribution: String? = null,
    val description: Description? = Description(),
    val ecology: Ecology? = Ecology(),
    val flowering: Flowering? = Flowering(),
    val images: List<Image> = listOf(),
    val nomenclature: Nomenclature? = Nomenclature(),
    val shortFamily: ShortTaxon = ShortTaxon(),
    val shortGenus: ShortTaxon = ShortTaxon(),
    val subspecies: List<Species> = listOf(),
    val taxonomy: Taxonomy? = Taxonomy(),
    val gbifId: Int? = null,
): Taxon