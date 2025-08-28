package com.floracatalana.floracatalana.presentation.screens.species_detail

sealed class SpeciesDetailEvent {
    data class SelectTab(val selectedTab: Int): SpeciesDetailEvent()
    data class ToggleImageDialog(val imageUrl: String? = null): SpeciesDetailEvent()
}