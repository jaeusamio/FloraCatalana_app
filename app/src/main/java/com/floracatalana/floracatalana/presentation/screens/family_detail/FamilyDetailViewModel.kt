package com.floracatalana.floracatalana.presentation.screens.family_detail

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.floracatalana.floracatalana.domain.repository.SpeciesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FamilyDetailViewModel @Inject constructor(
    private val speciesRepository: SpeciesRepository,
    savedStateHandle: SavedStateHandle
): ViewModel() {

    private val _state = mutableStateOf(FamilyDetailState())
    val state: State<FamilyDetailState> = _state

    init {
        savedStateHandle.get<String>("id")?.let { id ->
            viewModelScope.launch(Dispatchers.IO) {
                val families = speciesRepository.loadFamilies()
                val selectedFamily = families.first { id == it.code }
                _state.value = state.value.copy(
                    family = selectedFamily,
                    loading = false
                )
            }
        }
    }

    fun onEvent(event: FamilyDetailEvent) {

    }
}