package com.floracatalana.floracatalana.presentation.screens.search

sealed class SearchEvent {
    data class SearchSpecies(val value: String): SearchEvent()
}