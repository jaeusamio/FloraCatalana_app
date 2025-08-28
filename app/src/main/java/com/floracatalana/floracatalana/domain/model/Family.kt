package com.floracatalana.floracatalana.domain.model


import androidx.annotation.Keep

@Keep
data class Family(
    override val code: String = "",
    override val nodeId: String = "",
    override val rank: TaxonRank = TaxonRank.FAMILY,
    override val nameLatin: String = "",
    override val nameCat: String = "",
    override val url: String = "",
): Taxon