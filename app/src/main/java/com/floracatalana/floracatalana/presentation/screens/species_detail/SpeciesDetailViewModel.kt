package com.floracatalana.floracatalana.presentation.screens.species_detail

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.floracatalana.floracatalana.domain.repository.SpeciesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class SpeciesDetailViewModel(
    private val speciesRepository: SpeciesRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = mutableStateOf(SpeciesDetailState())
    val state: State<SpeciesDetailState> = _state

    init {
        savedStateHandle.get<String>("id")?.let { id ->
            viewModelScope.launch(Dispatchers.IO) {
                val species = speciesRepository.loadSpecies()
                val selectedSpecies =
                    species.firstOrNull { it.code == id } ?: species.flatMap { it.subspecies }
                        .first { it.code == id }
                val genera = speciesRepository.loadGenera()
                val selectedGenus = genera.first { selectedSpecies.shortGenus.code == it.code }
                val families = speciesRepository.loadFamilies()
                val selectedFamily = families.first { selectedSpecies.shortFamily.code == it.code }
                _state.value = state.value.copy(
                    species = selectedSpecies,
                    loading = false,
                    genus = selectedGenus,
                    family = selectedFamily
                )
            }
        }
    }

    fun onEvent(event: SpeciesDetailEvent) {
        when (event) {
            is SpeciesDetailEvent.SelectTab -> {
                _state.value = state.value.copy(selectedTab = event.selectedTab)
            }
        }
    }
}