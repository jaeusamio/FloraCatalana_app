package com.floracatalana.floracatalana.presentation.screens.subtaxa_list

import com.floracatalana.floracatalana.domain.model.Taxon

data class SubtaxaListState(
    val subtaxaList: List<Taxon> = emptyList(),
    val parentTaxon: Taxon? = null,
    val loading: Boolean = false
)
