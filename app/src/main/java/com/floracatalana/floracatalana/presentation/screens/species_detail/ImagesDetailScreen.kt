package com.floracatalana.floracatalana.presentation.screens.species_detail

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ImagesDetailScreen(
    state: SpeciesDetailState,
    navController: NavController
) {
    val images = state.species.images
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Flora Catalana") },
                navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Default.ArrowBack,
                            contentDescription = "Enrere"
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        LazyVerticalStaggeredGrid(
            modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp),
            columns = StaggeredGridCells.Fixed(3),
            userScrollEnabled = true
        ) {
            items(items = images, key = { it.url }) {
                AsyncImage(
                    model = it.url,
                    contentDescription = null,
                    modifier = Modifier.padding(4.dp)
                )
            }
        }
    }
}