package com.floracatalana.floracatalana.presentation.screens.genus_detail

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.floracatalana.floracatalana.data.remote.FloracatalanaApi
import com.floracatalana.floracatalana.domain.mappers.toGenus
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class GenusDetailViewModel(
    private val floracatalanaApi: FloracatalanaApi,
    savedStateHandle: SavedStateHandle
): ViewModel() {
    private val _state = mutableStateOf(GenusDetailState())
    val state: State<GenusDetailState> = _state

    init {
        savedStateHandle.get<String>("id")?.let { id ->
            viewModelScope.launch(Dispatchers.IO) {
                val genusData = floracatalanaApi.getGenusDetail(code = id)
                _state.value = state.value.copy(
                    genus = genusData.toGenus(),
                    loading = false
                )
            }
        }
    }

    fun onEvent(event: GenusDetailEvent) {

    }
}