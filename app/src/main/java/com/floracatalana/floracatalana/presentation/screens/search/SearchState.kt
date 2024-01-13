package com.floracatalana.floracatalana.presentation.screens.search

import com.floracatalana.floracatalana.domain.model.Species

data class SearchState(
    val speciesList: List<Species> = listOf(),
    val searchBarText: String = "",
    val autocompleteSpeciesList: List<Species> = listOf()
)
