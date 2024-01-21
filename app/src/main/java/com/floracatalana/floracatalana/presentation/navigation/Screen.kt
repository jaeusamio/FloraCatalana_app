package com.floracatalana.floracatalana.presentation.navigation

sealed class Screen(val route: String) {
    data object Search: Screen(route = "search")
    data object DetailSpecies: Screen(route = "detail_species/{id}") {
        fun passId(id: String) = "detail_species/$id"
    }
    data object DetailImages: Screen(route = "detail_images")
    data object DetailGenus: Screen(route = "detail_genus/{id}") {
        fun passId(id: String) = "detail_genus/$id"
    }
    data object DetailFamily: Screen(route = "detail_family/{id}") {
        fun passId(id: String) = "detail_family/$id"
    }
}