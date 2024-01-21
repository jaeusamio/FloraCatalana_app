package com.floracatalana.floracatalana.presentation.screens.genus_detail

import com.floracatalana.floracatalana.domain.model.Genus

data class GenusDetailState(
    val genus: Genus = Genus(),
    val loading: Boolean = true
)
