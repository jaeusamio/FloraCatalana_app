package com.floracatalana.floracatalana.presentation.screens.search

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.floracatalana.floracatalana.domain.repository.SpeciesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val speciesRepository: SpeciesRepository
): ViewModel() {

    private val _state = mutableStateOf(SearchState())
    val state: State<SearchState> = _state

    init {
        viewModelScope.launch(Dispatchers.IO) {
            val species = speciesRepository.loadSpecies()
            _state.value = state.value.copy(speciesList = species)
        }
    }

    fun onEvent(event: SearchEvent) {
        when(event) {
            is SearchEvent.SearchSpecies -> {

            }
        }
    }
}