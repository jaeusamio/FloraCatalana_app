package com.floracatalana.floracatalana.presentation.screens.family_detail

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.floracatalana.floracatalana.data.remote.FloracatalanaApi
import com.floracatalana.floracatalana.domain.mappers.toFamily
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class FamilyDetailViewModel(
    private val floracatalanaApi: FloracatalanaApi,
    savedStateHandle: SavedStateHandle
): ViewModel() {

    private val _state = mutableStateOf(FamilyDetailState())
    val state: State<FamilyDetailState> = _state

    init {
        savedStateHandle.get<String>("id")?.let { id ->
            viewModelScope.launch(Dispatchers.IO) {
                val familyData = floracatalanaApi.getFamilyDetail(code = id)
                _state.value = state.value.copy(
                    family = familyData.toFamily(),
                    loading = false
                )
            }
        }
    }

    fun onEvent(event: FamilyDetailEvent) {

    }
}