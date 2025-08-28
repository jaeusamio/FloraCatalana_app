package com.floracatalana.floracatalana.presentation.screens.family_detail

sealed class FamilyDetailEvent {
    data class SelectTab(val selectedTab: Int) : FamilyDetailEvent()

    data class ToggleImageDialog(val imageUrl: String? = null) : FamilyDetailEvent()
}