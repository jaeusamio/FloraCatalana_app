package com.floracatalana.floracatalana.presentation.screens.family_detail

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextLinkStyles
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.fromHtml
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.floracatalana.floracatalana.domain.model.TaxonRank
import com.floracatalana.floracatalana.presentation.navigation.Screen
import com.floracatalana.floracatalana.presentation.screens.species_detail.DescriptionSection
import com.floracatalana.floracatalana.presentation.screens.species_detail.EcologySection
import com.floracatalana.floracatalana.presentation.screens.species_detail.ImageCard
import com.floracatalana.floracatalana.presentation.screens.species_detail.ImageDialog
import com.floracatalana.floracatalana.util.DESCRIPTION
import com.floracatalana.floracatalana.util.ECOLOGY

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
                title = { },
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
                    Text(
                        text = family.nameCat,
                        style = MaterialTheme.typography.displaySmall,
                        textAlign = TextAlign.Center
                    )
                    Text(text = family.rank.label, color = MaterialTheme.colorScheme.tertiary)
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    SubtaxaSection(
                        label = "Llista de gèneres",
                        color = MaterialTheme.colorScheme.secondaryContainer,
                        contentColor = MaterialTheme.colorScheme.onSecondaryContainer,
                        onClick = {
                            navController.navigate(
                                Screen.SubtaxaList.passValues(
                                    id = family.nodeId,
                                    queriedRank = TaxonRank.FAMILY.id,
                                    returnedRank = TaxonRank.GENUS.id
                                )
                            )
                        },
                    )
                    SubtaxaSection(
                        label = "Llista de tàxons",
                        color = MaterialTheme.colorScheme.tertiaryContainer,
                        contentColor = MaterialTheme.colorScheme.onTertiaryContainer,
                        onClick = {
                            navController.navigate(
                                Screen.SubtaxaList.passValues(
                                    id = family.nodeId,
                                    queriedRank = TaxonRank.FAMILY.id,
                                    returnedRank = TaxonRank.SPECIES.id
                                )
                            )
                        },
                    )
                }
                if (family.categoryImages.isNotEmpty()) {
                    Spacer(modifier = Modifier.height(10.dp))
                    LazyRow {
                        items(items = family.categoryImages) { image ->
                            val annotatedString = AnnotatedString.fromHtml(
                                image.label,
                                linkStyles = TextLinkStyles(
                                    style = SpanStyle(
                                        fontStyle = FontStyle.Italic,
                                        fontSize = MaterialTheme.typography.bodySmall.fontSize,
                                        color = MaterialTheme.colorScheme.surfaceVariant
                                    )
                                )
                            )
                            ImageCard(
                                imageUrl = image.url,
                                label = annotatedString,
                                modifier = Modifier.size(127.dp)
                            )
                            Spacer(modifier = Modifier.width(10.dp))
                        }
                    }
                }

                val tabTitles = mutableListOf<String>()
                if (family.description != null) tabTitles.add(DESCRIPTION)
                if (family.ecology != null) tabTitles.add(ECOLOGY)

                if (tabTitles.isNotEmpty()) {
                    TabRow (
                        selectedTabIndex = state.selectedTab,
                        modifier = Modifier.fillMaxWidth().align(Alignment.CenterHorizontally)
                    ) {
                        tabTitles.forEachIndexed { index, title ->
                            Tab(
                                selected = state.selectedTab == index,
                                onClick = { onEvent(FamilyDetailEvent.SelectTab(index)) },
                                text = {
                                    Text(
                                        text = title,
                                        maxLines = 2,
                                        overflow = TextOverflow.Ellipsis
                                    )
                                }
                            )
                        }
                    }
                    if (tabTitles[state.selectedTab] == DESCRIPTION) {
                        family.description?.let {
                            DescriptionSection(
                                description = it,
                                onExpandImageClick = { url ->
                                    onEvent(FamilyDetailEvent.ToggleImageDialog(url))
                                }
                            )
                        }
                    }
                    if (tabTitles[state.selectedTab] == ECOLOGY) {
                        family.ecology?.let { EcologySection(ecology = it) }
                    }
                }
            }
        }
    }

    if (state.imageUrl != null) {
        ImageDialog(
            onDismissRequest = { onEvent(FamilyDetailEvent.ToggleImageDialog()) },
            state.imageUrl
        )
    }
}

@Composable
fun RowScope.SubtaxaSection(
    onClick: () -> Unit,
    label: String,
    color: Color,
    contentColor: Color,
) {
    Surface(
        modifier = Modifier
            .weight(1f)
            .clickable { onClick() },
        color = color,
        contentColor = contentColor,
        shape = RoundedCornerShape(10.dp)
    ) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            text = label,
            style = MaterialTheme.typography.titleMedium,
            textAlign = TextAlign.Center,
            textDecoration = TextDecoration.Underline
        )
    }
}