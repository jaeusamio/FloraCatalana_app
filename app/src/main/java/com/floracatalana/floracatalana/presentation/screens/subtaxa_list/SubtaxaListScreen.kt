package com.floracatalana.floracatalana.presentation.screens.subtaxa_list

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.floracatalana.floracatalana.domain.model.Genus
import com.floracatalana.floracatalana.domain.model.species.Species
import com.floracatalana.floracatalana.presentation.navigation.Screen
import com.floracatalana.floracatalana.presentation.screens.search.GenusCard
import com.floracatalana.floracatalana.presentation.screens.search.SpeciesCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SubtaxaListScreen(
    navController: NavController,
    state: SubtaxaListState
) {
    val subtaxaList = state.subtaxaList

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    val parentTaxon = state.parentTaxon
                    if (parentTaxon != null) {
                        val formattedRank =
                            parentTaxon.rank.label.lowercase().replaceFirstChar { it.titlecase() }
                        Text(text = "$formattedRank ${parentTaxon.nameCat ?: parentTaxon.nameLatin}")
                    }
                },
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
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 16.dp)
        ) {
            val listSize = subtaxaList.size
            val rankText = if (subtaxaList.filterIsInstance<Species>().size == listSize) {
                if (listSize != 1) "tàxons" else "tàxon"
            } else {
                if (listSize != 1) "gèneres" else "gènere"
            }

            Text(
                text = "$listSize $rankText",
                color = MaterialTheme.colorScheme.outline,
                fontStyle = FontStyle.Italic
            )
            LazyColumn {
                if (subtaxaList.filterIsInstance<Species>().size == listSize) {
                    items(items = subtaxaList, key = null) {
                        SpeciesCard(
                            species = it as Species
                        ) { navController.navigate(Screen.DetailSpecies.passId(it.code)) }
                        Spacer(modifier = Modifier.height(8.dp))
                    }
                } else if (subtaxaList.filterIsInstance<Genus>().size == listSize) {
                    items(items = subtaxaList, key = null) {
                        GenusCard(
                            genus = it as Genus,
                            onClick = { navController.navigate(Screen.DetailGenus.passId(it.nodeId)) }
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                    }
                }
            }
        }
    }
}