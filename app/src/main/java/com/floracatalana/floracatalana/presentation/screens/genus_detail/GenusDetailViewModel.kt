package com.floracatalana.floracatalana.presentation.screens.genus_detail

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
class GenusDetailViewModel(
    private val speciesRepository: SpeciesRepository,
    savedStateHandle: SavedStateHandle
): ViewModel() {
    private val _state = mutableStateOf(GenusDetailState())
    val state: State<GenusDetailState> = _state

    init {
        savedStateHandle.get<String>("id")?.let { id ->
            viewModelScope.launch(Dispatchers.IO) {
                val genera = speciesRepository.loadGenera()
                val selectedGenus = genera.first { id == it.code }
                _state.value = state.value.copy(
                    genus = selectedGenus,
                    loading = false
                )
            }
        }
    }

    fun onEvent(event: GenusDetailEvent) {

    }
}