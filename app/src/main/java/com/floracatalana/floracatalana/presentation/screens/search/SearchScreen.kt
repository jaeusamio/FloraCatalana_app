package com.floracatalana.floracatalana.presentation.screens.search

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.floracatalana.floracatalana.domain.model.Image
import com.floracatalana.floracatalana.domain.model.Species

@Composable
fun SearchScreen(
    state: SearchState,
    event: (SearchEvent) -> Unit
) {
    Column(modifier = Modifier.padding(16.dp)) {
        LazyColumn {
            items(items = state.speciesList, key = { it.code }) {
                SpeciesCard(species = it)
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}

@Preview
@Composable
fun SpeciesCard(
    species: Species = Species(
        nameCat = "Herba d'all",
        nameLatin = "Alliaria petiolata",
        genusCode = "Gen201",
        familyCode = "Fam43",
        images = listOf(
            Image(
                tags = null,
                url = "https://www.floracatalana.cat/flora/sites/default/files/imgvasculars/2019-06/mgc1/VTax1004.ExN.Img10.jpg"
            )
        )
    )
) {
    Card(modifier = Modifier.fillMaxWidth()) {
        Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
            if (species.images.isNotEmpty()) {
                AsyncImage(
                    model = species.images[0].url,
                    contentDescription = null,
                    modifier = Modifier.size(100.dp),
                    contentScale = ContentScale.Crop
                )
            } else {
                Box(modifier = Modifier.size(100.dp))
            }
            Column {
                Text(text = "Nom: ${species.nameLatin}")
                Text(text = "Gènere: ${species.genusCode}")
                Text(text = "Família: ${species.familyCode}")
                if (species.subspecies.isNotEmpty()) {
                    Text(text = "${species.subspecies.size} subespècies")
                }
            }
        }
    }
}