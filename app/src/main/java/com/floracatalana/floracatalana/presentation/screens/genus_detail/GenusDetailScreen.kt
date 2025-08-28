package com.floracatalana.floracatalana.presentation.screens.genus_detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GenusDetailScreen(
    state: GenusDetailState,
    onEvent: (GenusDetailEvent) -> Unit,
    navController: NavController
) {
    val genus = state.genus
    Scaffold(
        topBar = {
            TopAppBar(
                title = {  },
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
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(horizontal = 16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            if (state.loading) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    CircularProgressIndicator()
                }
            } else {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = genus.rank, color = MaterialTheme.colorScheme.tertiary)
                    Text(
                        text = genus.nameLatin,
                        style = MaterialTheme.typography.displaySmall,
                        textAlign = TextAlign.Center
                    )
                    SuggestionChip(
                        onClick = { /* navController.navigate(Screen.DetailFamily.passId(genus.shortFamily.code)) */ },
                        label = {
                            Text(text = "Família")
                        }
                    )
                    Column(
                        horizontalAlignment = Alignment.Start,
                        modifier = Modifier
                            .padding(vertical = 16.dp)
                            .align(Alignment.Start)
                    ) {
//                        Text(text = "Subtàxons", style = MaterialTheme.typography.titleLarge)
//                        Surface(
//                            color = MaterialTheme.colorScheme.primaryContainer,
//                            shape = RoundedCornerShape(10.dp),
//                            modifier = Modifier.widthIn(min = 250.dp)
//                        ) {
//                            Column(modifier = Modifier.padding(12.dp)) {
//                                genus.species.forEach { species ->
//                                    Text(
//                                        text = species.name,
//                                        textDecoration = TextDecoration.Underline,
//                                        fontWeight = FontWeight.Bold,
//                                        color = MaterialTheme.colorScheme.primary,
//                                        modifier = Modifier
//                                            .padding(vertical = 8.dp)
//                                            .clickable {
//                                                navController.navigate(
//                                                    Screen.DetailSpecies.passId(species.code)
//                                                )
//                                            }
//                                    )
//                                }
//                            }
//                        }
                    }
                }
            }
        }
    }
}