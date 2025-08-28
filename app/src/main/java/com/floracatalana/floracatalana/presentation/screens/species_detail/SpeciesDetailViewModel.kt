package com.floracatalana.floracatalana.presentation.screens.species_detail

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.floracatalana.floracatalana.data.remote.FloracatalanaApi
import com.floracatalana.floracatalana.domain.mappers.toSpecies
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class SpeciesDetailViewModel(
    private val floracatalanaApi: FloracatalanaApi,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = mutableStateOf(SpeciesDetailState())
    val state: State<SpeciesDetailState> = _state

    init {
        savedStateHandle.get<String>("id")?.let { id ->
            viewModelScope.launch(Dispatchers.IO) {
                val speciesData = floracatalanaApi.getSpeciesDetail(code = id)
                _state.value = state.value.copy(
                    species = speciesData.toSpecies(),
                    loading = false,
                )
            }
        }
    }

    fun onEvent(event: SpeciesDetailEvent) {
        when (event) {
            is SpeciesDetailEvent.SelectTab -> {
                _state.value = state.value.copy(selectedTab = event.selectedTab)
            }

            is SpeciesDetailEvent.ToggleImageDialog -> {
                _state.value = state.value.copy(imageUrl = event.imageUrl)
            }
        }
    }
}