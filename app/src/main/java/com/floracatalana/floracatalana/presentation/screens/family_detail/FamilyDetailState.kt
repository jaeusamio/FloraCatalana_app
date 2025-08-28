package com.floracatalana.floracatalana.presentation.screens.family_detail

import com.floracatalana.floracatalana.domain.model.Family

data class FamilyDetailState(
    val family: Family = Family(),
    val selectedTab: Int = 0,
    val imageUrl: String? = null,
    val loading: Boolean = true
)
