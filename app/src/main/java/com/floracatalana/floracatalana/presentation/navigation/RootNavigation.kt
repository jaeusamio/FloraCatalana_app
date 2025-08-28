package com.floracatalana.floracatalana.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.floracatalana.floracatalana.presentation.screens.family_detail.FamilyDetailScreen
import com.floracatalana.floracatalana.presentation.screens.family_detail.FamilyDetailViewModel
import com.floracatalana.floracatalana.presentation.screens.genus_detail.GenusDetailScreen
import com.floracatalana.floracatalana.presentation.screens.genus_detail.GenusDetailViewModel
import com.floracatalana.floracatalana.presentation.screens.search.SearchScreen
import com.floracatalana.floracatalana.presentation.screens.search.SearchViewModel
import com.floracatalana.floracatalana.presentation.screens.species_detail.ImagesDetailScreen
import com.floracatalana.floracatalana.presentation.screens.species_detail.SpeciesDetailScreen
import com.floracatalana.floracatalana.presentation.screens.species_detail.SpeciesDetailViewModel
import com.floracatalana.floracatalana.presentation.screens.subtaxa_list.SubtaxaListScreen
import com.floracatalana.floracatalana.presentation.screens.subtaxa_list.SubtaxaListViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun RootNavGraph(
    navController: NavHostController,
    startDestination: String
) {
    NavHost(navController = navController, startDestination = startDestination) {
        composable(route = Screen.Search.route) {
            val viewModel = koinViewModel<SearchViewModel>()
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
            val viewModel: SpeciesDetailViewModel = koinViewModel()
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
            val viewModel = koinViewModel<GenusDetailViewModel>()
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
            val viewModel = koinViewModel<FamilyDetailViewModel>()
            FamilyDetailScreen(
                state = viewModel.state.value,
                navController = navController,
                onEvent = viewModel::onEvent
            )
        }

        composable(route = Screen.DetailImages.route) { backStackEntry ->
            // TODO: This could be a separate viewmodel calling a specific endpoint of the API
            val detailSpeciesBackStackEntry = remember(backStackEntry) {
                try {
                    navController.getBackStackEntry(Screen.DetailSpecies.route)
                } catch (e: IllegalArgumentException) {
                    backStackEntry // fallback to current entry
                }
            }
            val viewModel: SpeciesDetailViewModel = koinViewModel(viewModelStoreOwner = detailSpeciesBackStackEntry)
            ImagesDetailScreen(
                state = viewModel.state.value,
                navController = navController,
            )
        }

        composable(
            route = Screen.SubtaxaList.route,
            arguments = listOf(
                navArgument(name = "id") { type = NavType.StringType },
                navArgument(name = "queried_rank") { type = NavType.IntType },
                navArgument(name = "returned_rank") { type = NavType.IntType }
            )
        ) {
            val viewModel: SubtaxaListViewModel = koinViewModel()
            SubtaxaListScreen(
                navController = navController,
                state = viewModel.state.collectAsStateWithLifecycle().value
            )
        }
    }
}