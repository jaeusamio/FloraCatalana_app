package com.floracatalana.floracatalana.presentation.screens.search

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.floracatalana.floracatalana.domain.repository.SpeciesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class SearchViewModel(
    private val speciesRepository: SpeciesRepository
) : ViewModel() {

    private val _state = mutableStateOf(SearchState())
    val state: State<SearchState> = _state

    init {
        viewModelScope.launch(Dispatchers.IO) {
            val species = speciesRepository.loadSpecies()
            val genera = speciesRepository.loadGenera()
            val families = speciesRepository.loadFamilies()
            _state.value = state.value.copy(
                speciesList = species,
                autocompleteSpeciesList = species,
                generaList = genera,
                autocompleteGeneraList = genera,
                familyList = families,
                autocompleteFamilyList = families,
                loading = false,
            )
        }
    }

    fun onEvent(event: SearchEvent) {
        when (event) {
            is SearchEvent.SearchSpecies -> {
                val trimmedText = event.value.trim()
                val autocompleteSpeciesList = if (trimmedText.length > 2) {
                    state.value.speciesList.filter {
                        it.nameLatin.lowercase().contains(trimmedText.lowercase())
                    }
                } else state.value.speciesList
                _state.value = state.value.copy(
                    searchBarText = event.value,
                    autocompleteSpeciesList = autocompleteSpeciesList
                )
            }
            is SearchEvent.SelectTab -> {
                _state.value = state.value.copy(selectedTab = event.selectedTab)
                when(state.value.selectedTab) {
                    TaxonListTab.SPECIES -> onEvent(SearchEvent.SearchSpecies(state.value.searchBarText))
                    TaxonListTab.GENERA -> onEvent(SearchEvent.SearchGenera(state.value.searchBarText))
                    TaxonListTab.FAMILIES -> onEvent(SearchEvent.SearchFamilies(state.value.searchBarText))
                }
            }
            is SearchEvent.SearchFamilies -> {
                val trimmedText = event.value.trim()
                val autocompleteFamiliesList = if (trimmedText.length > 2) {
                    state.value.familyList.filter {
                        it.nameLatin.lowercase().contains(event.value.lowercase()) ||
                                it.nameCat.lowercase().contains(trimmedText.lowercase())
                    }
                } else state.value.familyList
                _state.value = state.value.copy(
                    searchBarText = event.value,
                    autocompleteFamilyList = autocompleteFamiliesList
                )
            }
            is SearchEvent.SearchGenera -> {
                val trimmedText = event.value.trim()
                val autocompleteGeneraList = if (trimmedText.length > 2) {
                    state.value.generaList.filter {
                        it.nameLatin.lowercase().contains(trimmedText.lowercase())
                    }
                } else state.value.generaList
                _state.value = state.value.copy(
                    searchBarText = event.value,
                    autocompleteGeneraList = autocompleteGeneraList
                )
            }
            SearchEvent.CleanSearchBar -> {
                _state.value = state.value.copy(
                    searchBarText = "",
                    autocompleteFamilyList = state.value.familyList,
                    autocompleteGeneraList = state.value.generaList,
                    autocompleteSpeciesList = state.value.speciesList
                )
            }
        }
    }
}