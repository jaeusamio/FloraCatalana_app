package com.floracatalana.floracatalana.presentation.screens.family_detail

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
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FamilyDetailScreen(
    state: FamilyDetailState,
    onEvent: (FamilyDetailEvent) -> Unit,
    navController: NavController
) {
    val family = state.family
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
                    Text(text = family.rank, color = MaterialTheme.colorScheme.tertiary)
//                    Column(
//                        horizontalAlignment = Alignment.Start,
//                        modifier = Modifier
//                            .padding(vertical = 16.dp)
//                            .align(Alignment.Start)
//                    ) {
//                        Text(text = "Gèneres", style = MaterialTheme.typography.titleLarge)
//                        Surface(
//                            color = MaterialTheme.colorScheme.primaryContainer,
//                            shape = RoundedCornerShape(10.dp),
//                            modifier = Modifier.widthIn(min = 250.dp)
//                        ) {
//                            Column(modifier = Modifier.padding(12.dp)) {
//                                family.genera.forEach { species ->
//                                    Text(
//                                        text = species.name,
//                                        textDecoration = TextDecoration.Underline,
//                                        fontWeight = FontWeight.Bold,
//                                        color = MaterialTheme.colorScheme.primary,
//                                        modifier = Modifier
//                                            .padding(vertical = 8.dp)
//                                            .clickable {
//                                                navController.navigate(
//                                                    Screen.DetailGenus.passId(species.code)
//                                                )
//                                            }
//                                    )
//                                }
//                            }
//                        }
//                    }
                    Text(
                        text = family.nameCat,
                        style = MaterialTheme.typography.displaySmall,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}