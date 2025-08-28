package com.floracatalana.floracatalana.presentation.screens.search

import com.floracatalana.floracatalana.domain.model.Family
import com.floracatalana.floracatalana.domain.model.Genus
import com.floracatalana.floracatalana.domain.model.species.Species

// FIXME: Migrate to Taxon interface to simplify fields
data class SearchState(
    val speciesList: List<Species> = listOf(),
    val generaList: List<Genus> = listOf(),
    val familyList: List<Family> = listOf(),
    val searchBarText: String = "",
    val autocompleteSpeciesList: List<Species> = listOf(),
    val autocompleteGeneraList: List<Genus> = listOf(),
    val autocompleteFamilyList: List<Family> = listOf(),
    val loading: Boolean = true,
    val selectedTab: TaxonListTab = TaxonListTab.SPECIES
)
