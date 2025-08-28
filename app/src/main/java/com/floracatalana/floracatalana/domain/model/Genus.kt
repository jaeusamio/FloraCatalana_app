package com.floracatalana.floracatalana.domain.model


import androidx.annotation.Keep

@Keep
data class Genus(
    override val code: String = "",
    override val nodeId: String = "",
    override val rank: TaxonRank = TaxonRank.GENUS,
    override val nameLatin: String = "",
    override val nameCat: String? = null,
    override val url: String = "",
    val shortFamily: ShortTaxon = ShortTaxon(),
): Taxon