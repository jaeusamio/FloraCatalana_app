package com.floracatalana.floracatalana.presentation.screens.subtaxa_list

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.floracatalana.floracatalana.data.remote.FloracatalanaApi
import com.floracatalana.floracatalana.domain.mappers.toFamily
import com.floracatalana.floracatalana.domain.mappers.toGenus
import com.floracatalana.floracatalana.domain.mappers.toSpecies
import com.floracatalana.floracatalana.domain.model.TaxonRank
import com.floracatalana.floracatalana.util.TAG
import io.ktor.serialization.JsonConvertException
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class SubtaxaListViewModel(
    private val floracatalanaApi: FloracatalanaApi,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = MutableStateFlow(SubtaxaListState())
    val state: StateFlow<SubtaxaListState> = _state

    init {
        viewModelScope.launch {
            _state.update { it.copy(loading = true) }
            val id = savedStateHandle.get<String>("id")
            val queriedRankId = savedStateHandle.get<Int>("queried_rank")
            val returnedRankId = savedStateHandle.get<Int>("returned_rank")
            if (id != null && queriedRankId != null && returnedRankId != null) {
                try {
                    val queriedRank = TaxonRank.entries.first { it.id == queriedRankId }
                    val returnedRank = TaxonRank.entries.first { it.id == returnedRankId }

                    if (returnedRank == TaxonRank.SPECIES) {
                        if (queriedRank == TaxonRank.GENUS) {
                            val parentGenus = floracatalanaApi.getGenusDetail(id).toGenus()
                            val species =
                                floracatalanaApi.getSpeciesList(genusCode = parentGenus.code).map { it.toSpecies() }
                            _state.update {
                                it.copy(
                                    subtaxaList = species,
                                    parentTaxon = parentGenus,
                                    loading = false
                                )
                            }
                        }
                        if (queriedRank == TaxonRank.FAMILY) {
                            val parentFamily = floracatalanaApi.getFamilyDetail(id).toFamily()
                            val species =
                                floracatalanaApi.getSpeciesList(familyCode = parentFamily.code).map { it.toSpecies() }
                            _state.update {
                                it.copy(
                                    subtaxaList = species,
                                    parentTaxon = parentFamily,
                                    loading = false
                                )
                            }
                        }
                    }

                    if (returnedRank == TaxonRank.GENUS) {
                        val parentFamily = floracatalanaApi.getFamilyDetail(id).toFamily()
                        val genera = floracatalanaApi.getGenusList(familyCode = parentFamily.code).map { it.toGenus() }
                        _state.update {
                            it.copy(
                                subtaxaList = genera,
                                parentTaxon = parentFamily,
                                loading = false
                            )
                        }
                    }
                } catch (e: JsonConvertException) {
                    Log.e(TAG, "${e.cause}: ${e.message}", )
                }
            }
        }
    }
}