@file:Suppress("UNCHECKED_CAST")

package com.floracatalana.floracatalana.presentation.screens.search

import android.view.ViewTreeObserver
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Grass
import androidx.compose.material.icons.filled.LocalFlorist
import androidx.compose.material.icons.filled.Park
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.PrimaryTabRow
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.floracatalana.floracatalana.R
import com.floracatalana.floracatalana.domain.model.Family
import com.floracatalana.floracatalana.domain.model.Genus
import com.floracatalana.floracatalana.domain.model.species.Image
import com.floracatalana.floracatalana.domain.model.species.Species
import com.floracatalana.floracatalana.presentation.navigation.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(
    navController: NavController,
    state: SearchState,
    onEvent: (SearchEvent) -> Unit
) {
    val focusManager = LocalFocusManager.current
    val isKeyboardOpen by keyboardAsState()
    if (!isKeyboardOpen) focusManager.clearFocus()
    Scaffold(
        topBar = {
            Box(modifier = Modifier.fillMaxWidth()) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 12.dp)
                        .padding(top = 12.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.logo),
                        contentDescription = null,
                        modifier = Modifier.size(45.dp)
                    )
                    val keyboardController = LocalSoftwareKeyboardController.current
                    OutlinedTextField(
                        modifier = Modifier.fillMaxWidth(),
                        value = state.searchBarText,
                        placeholder = {
                            Text(
                                text = "Cerca ${state.selectedTab.value.lowercase()}",
                                modifier = Modifier.alpha(alpha = 0.5f)
                            )
                        },
                        singleLine = true,
                        maxLines = 1,
                        onValueChange = {
                            when (state.selectedTab) {
                                TaxonListTab.SPECIES -> onEvent(SearchEvent.SearchSpecies(it))
                                TaxonListTab.GENERA -> onEvent(SearchEvent.SearchGenera(it))
                                TaxonListTab.FAMILIES -> onEvent(SearchEvent.SearchFamilies(it))
                            }
                        },
                        trailingIcon = {
                            if (state.searchBarText.isNotEmpty()) {
                                IconButton(onClick = {
                                    onEvent(SearchEvent.CleanSearchBar)
                                    focusManager.clearFocus()
                                }) {
                                    Icon(
                                        imageVector = Icons.Default.Close,
                                        contentDescription = "Neteja la cerca"
                                    )
                                }
                            }
                        },
                        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                        keyboardActions = KeyboardActions(onDone = {
                            keyboardController?.hide()
                            focusManager.clearFocus()
                        }),
//                        focusRequester = focusRequester
                    )
                }
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            if (state.loading) {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxSize()
                ) {
                    CircularProgressIndicator()
                }
            } else {
                PrimaryTabRow(selectedTabIndex = state.selectedTab.index) {
                    TaxonListTab.entries.forEach { tab ->
                        Tab(
                            selected = state.selectedTab == tab,
                            onClick = {
                                onEvent(SearchEvent.SelectTab(selectedTab = tab))
                            },
                            icon = { Icon(imageVector = tab.icon, contentDescription = null) },
                            text = { Text(text = tab.value) }
                        )
                    }
                }
                val items = when (state.selectedTab) {
                    TaxonListTab.SPECIES -> state.autocompleteSpeciesList
                    TaxonListTab.GENERA -> state.autocompleteGeneraList
                    TaxonListTab.FAMILIES -> state.autocompleteFamilyList
                }
                Text(
                    text = "${items.size} resultats",
                    color = MaterialTheme.colorScheme.outline,
                    fontStyle = FontStyle.Italic
                )
                LazyColumn {
                    if (items.filterIsInstance<Species>().size == items.size) {
                        items as List<Species>
                        items(items = items, key = null) {
                            SpeciesCard(
                                species = it
                            ) { navController.navigate(Screen.DetailSpecies.passId(it.code)) }
                            Spacer(modifier = Modifier.height(8.dp))
                        }
                    } else if (items.filterIsInstance<Genus>().size == items.size) {
                        items as List<Genus>
                        items(items = items, key = null) {
                            GenusCard(
                                genus = it,
                                onClick = { navController.navigate(Screen.DetailGenus.passId(it.code)) }
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                        }
                    } else {
                        items as List<Family>
                        items(items = items, key = null) {
                            FamilyCard(
                                family = it,
                                onClick = { navController.navigate(Screen.DetailFamily.passId(it.code)) }
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                        }
                    }
                }
            }
        }
    }
}

enum class TaxonListTab(
    val index: Int,
    val value: String,
    val icon: ImageVector
) {
    SPECIES(value = "Espècies", icon = Icons.Default.Grass, index = 0),
    GENERA(value = "Gèneres", icon = Icons.Default.LocalFlorist, index = 1),
    FAMILIES(value = "Famílies", icon = Icons.Default.Park, index = 2)
}

@Preview
@Composable
fun SpeciesCard(
    species: Species = Species(
        nameCat = "Herba d'all",
        nameLatin = "Alliaria petiolata",
        images = listOf(
            Image(
                tags = null,
                url = "https://www.floracatalana.cat/flora/sites/default/files/imgvasculars/2019-06/mgc1/VTax1004.ExN.Img10.jpg"
            )
        )
    ),
    onClick: () -> Unit = {}
) {
    val numberOfSubspeciesText = if (species.subspecies.isNotEmpty()) {
        if (species.subspecies.size == 1) "1 subespècie" else "${species.subspecies.size} subespècies"
    } else null
    BaseCard(
        title = species.nameLatin,
        subtitle = { Text(text = species.shortFamily.name) },
        numberOfTaxaText = numberOfSubspeciesText,
        imageUrl = if (species.images.isNotEmpty()) species.images[0].url else null,
        icon = Icons.Default.Grass,
        onClick = onClick
    )
}

@Composable
fun GenusCard(
    genus: Genus,
    onClick: () -> Unit
) {
    val numberOfSpeciesText = if (genus.nSpecies == 1) "1 espècie" else "${genus.nSpecies} espècies"
    BaseCard(
        title = genus.nameLatin,
        subtitle = { Text(text = genus.shortFamily.name) },
        numberOfTaxaText = numberOfSpeciesText,
        imageUrl = null,
        icon = Icons.Default.LocalFlorist,
        onClick = onClick
    )
}

@Composable
fun FamilyCard(
    family: Family,
    onClick: () -> Unit
) {
    val numberOfGeneraText = if (family.nGenera == 1) "1 gènere" else "${family.nGenera} gèneres"
    BaseCard(
        title = family.nameCat,
        subtitle = { Text(text = family.nameLatin, fontStyle = FontStyle.Italic) },
        numberOfTaxaText = numberOfGeneraText,
        imageUrl = null,
        icon = Icons.Default.Park,
        onClick = onClick
    )
}

@Composable
fun BaseCard(
    title: String,
    subtitle: @Composable (() -> Unit)?,
    numberOfTaxaText: String?,
    imageUrl: String?,
    icon: ImageVector,
    onClick: () -> Unit
) {
    Card(modifier = Modifier
        .fillMaxWidth()
        .clickable { onClick() }) {
        Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
            if (imageUrl != null) {
                AsyncImage(
                    model = imageUrl,
                    contentDescription = null,
                    modifier = Modifier
                        .size(80.dp)
                        .clip(RoundedCornerShape(topEnd = 30.dp)),
                    contentScale = ContentScale.Crop
                )
            } else {
                Box(
                    modifier = Modifier
                        .size(80.dp)
                        .clip(RoundedCornerShape(topEnd = 30.dp))
                ) {
                    Icon(
                        imageVector = icon,
                        contentDescription = null,
                        modifier = Modifier
                            .size(36.dp)
                            .align(Alignment.Center)
                    )
                }
            }
            Column(modifier = Modifier.padding(8.dp)) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.secondary
                )
                if (subtitle != null) subtitle()
                if (numberOfTaxaText != null) {
                    Spacer(modifier = Modifier.weight(1f))
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.End,
                    ) {
                        Text(
                            text = numberOfTaxaText,
                            style = MaterialTheme.typography.labelSmall,
                            color = MaterialTheme.colorScheme.outline
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun keyboardAsState(): State<Boolean> {
    val keyboardState = remember { mutableStateOf(false) }
    val view = LocalView.current
    val viewTreeObserver = view.viewTreeObserver
    DisposableEffect(viewTreeObserver) {
        val listener = ViewTreeObserver.OnGlobalLayoutListener {
            keyboardState.value = ViewCompat.getRootWindowInsets(view)
                ?.isVisible(WindowInsetsCompat.Type.ime()) ?: true
        }
        viewTreeObserver.addOnGlobalLayoutListener(listener)
        onDispose { viewTreeObserver.removeOnGlobalLayoutListener(listener) }
    }
    return keyboardState
}