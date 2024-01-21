package com.floracatalana.floracatalana.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.floracatalana.floracatalana.presentation.screens.family_detail.FamilyDetailScreen
import com.floracatalana.floracatalana.presentation.screens.family_detail.FamilyDetailViewModel
import com.floracatalana.floracatalana.presentation.screens.genus_detail.GenusDetailScreen
import com.floracatalana.floracatalana.presentation.screens.genus_detail.GenusDetailViewModel
import com.floracatalana.floracatalana.presentation.screens.species_detail.SpeciesDetailScreen
import com.floracatalana.floracatalana.presentation.screens.species_detail.SpeciesDetailViewModel
import com.floracatalana.floracatalana.presentation.screens.search.SearchScreen
import com.floracatalana.floracatalana.presentation.screens.search.SearchViewModel
import com.floracatalana.floracatalana.presentation.screens.species_detail.ImagesDetailScreen

@Composable
fun RootNavGraph(
    navController: NavHostController,
    startDestination: String
) {
    NavHost(navController = navController, startDestination = startDestination) {
        composable(route = Screen.Search.route) {
            val viewModel = hiltViewModel<SearchViewModel>()
            SearchScreen(
                state = viewModel.state.value,
                onEvent = viewModel::onEvent,
                navController = navController
            )
        }

        composable(
            route = Screen.DetailSpecies.route,
            arguments = listOf(navArgument(name = "id") { type = NavType.StringType })
        ) { backStackEntry ->
            val detailSpeciesBackStackEntry = remember(backStackEntry) {
                navController.getBackStackEntry(Screen.DetailSpecies.route)
            }
            val viewModel: SpeciesDetailViewModel = hiltViewModel(detailSpeciesBackStackEntry)
            SpeciesDetailScreen(
                state = viewModel.state.value,
                navController = navController,
                onEvent = viewModel::onEvent
            )
        }

        composable(
            route = Screen.DetailGenus.route,
            arguments = listOf(navArgument(name = "id") { type = NavType.StringType })
        ) {
            val viewModel = hiltViewModel<GenusDetailViewModel>()
            GenusDetailScreen(
                state = viewModel.state.value,
                navController = navController,
                onEvent = viewModel::onEvent
            )
        }

        composable(
            route = Screen.DetailFamily.route,
            arguments = listOf(navArgument(name = "id") { type = NavType.StringType })
        ) {
            val viewModel = hiltViewModel<FamilyDetailViewModel>()
            FamilyDetailScreen(
                state = viewModel.state.value,
                navController = navController,
                onEvent = viewModel::onEvent
            )
        }

        composable(route = Screen.DetailImages.route) {backStackEntry ->
            val detailSpeciesBackStackEntry = remember(backStackEntry) {
                navController.getBackStackEntry(Screen.DetailSpecies.route)
            }
            val viewModel: SpeciesDetailViewModel = hiltViewModel(detailSpeciesBackStackEntry)
            ImagesDetailScreen(
                state = viewModel.state.value,
                navController = navController,
            )
        }
    }
}