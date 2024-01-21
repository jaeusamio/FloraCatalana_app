package com.floracatalana.floracatalana.presentation.screens.species_detail

import com.floracatalana.floracatalana.domain.model.Family
import com.floracatalana.floracatalana.domain.model.Genus
import com.floracatalana.floracatalana.domain.model.species.Species

data class SpeciesDetailState(
    val species: Species = Species(),
    val family: Family = Family(),
    val genus: Genus = Genus(),
    val loading: Boolean = true,
    val selectedTab: Int = 0
)
