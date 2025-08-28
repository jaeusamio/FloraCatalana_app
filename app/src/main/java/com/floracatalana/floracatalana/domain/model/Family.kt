package com.floracatalana.floracatalana.domain.model


import androidx.annotation.Keep
import com.floracatalana.floracatalana.domain.model.species.CategoryImage
import com.floracatalana.floracatalana.domain.model.species.Description
import com.floracatalana.floracatalana.domain.model.species.Ecology

@Keep
data class Family(
    override val code: String = "",
    override val nodeId: String = "",
    override val rank: TaxonRank = TaxonRank.FAMILY,
    override val nameLatin: String = "",
    override val nameCat: String = "",
    override val url: String = "",
    val categoryImages: List<CategoryImage> = emptyList(),
    val description: Description? = null,
    val usos: String? = null,
    val ecology: Ecology? = null,
): Taxon