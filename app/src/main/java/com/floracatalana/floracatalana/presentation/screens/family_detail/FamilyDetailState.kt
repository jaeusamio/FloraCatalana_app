package com.floracatalana.floracatalana.presentation.screens.family_detail

import com.floracatalana.floracatalana.domain.model.Family

data class FamilyDetailState(
    val family: Family = Family(),
    val loading: Boolean = true
)
