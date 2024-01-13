package com.floracatalana.floracatalana.presentation.navigation

sealed class Screen(val route: String) {
    data object Search: Screen(route = "search")
    data object DetailSpecies: Screen(route = "detail_species/{id}") {
        fun passId(id: String) = "detail_species/$id"
    }
}