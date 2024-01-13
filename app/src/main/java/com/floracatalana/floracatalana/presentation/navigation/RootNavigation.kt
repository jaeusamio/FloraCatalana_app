package com.floracatalana.floracatalana.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.floracatalana.floracatalana.presentation.screens.search.SearchScreen
import com.floracatalana.floracatalana.presentation.screens.search.SearchViewModel

@Composable
fun RootNavGraph(
    navController: NavHostController,
    startDestination: String
) {
    NavHost(navController = navController, startDestination = startDestination) {
        composable(route = Screen.Search.route) {
            val viewModel = hiltViewModel<SearchViewModel>()
            SearchScreen(state = viewModel.state.value, event = viewModel::onEvent)
        }
    }
}