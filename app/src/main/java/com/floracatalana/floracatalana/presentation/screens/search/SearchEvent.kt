package com.floracatalana.floracatalana.presentation.screens.search

sealed class SearchEvent {
    data class SearchSpecies(val value: String): SearchEvent()
    data class SearchGenera(val value: String): SearchEvent()
    data class SearchFamilies(val value: String): SearchEvent()
    data object CleanSearchBar: SearchEvent()
    data class SelectTab(val selectedTab: TaxonListTab): SearchEvent()
}