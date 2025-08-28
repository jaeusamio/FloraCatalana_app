package com.floracatalana.floracatalana.presentation.screens.species_detail

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.outlined.CloudOff
import androidx.compose.material.icons.outlined.FilterVintage
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SecondaryScrollableTabRow
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.floracatalana.floracatalana.data.remote.HttpRoutes
import com.floracatalana.floracatalana.domain.model.ShortTaxon
import com.floracatalana.floracatalana.domain.model.species.Description
import com.floracatalana.floracatalana.domain.model.species.Ecology
import com.floracatalana.floracatalana.domain.model.species.Flowering
import com.floracatalana.floracatalana.domain.model.species.Nomenclature
import com.floracatalana.floracatalana.domain.model.species.Taxonomy
import com.floracatalana.floracatalana.presentation.navigation.Screen
import com.floracatalana.floracatalana.util.DESCRIPTION
import com.floracatalana.floracatalana.util.DISTRIBUTION
import com.floracatalana.floracatalana.util.ECOLOGY
import com.floracatalana.floracatalana.util.FLOWERING
import com.floracatalana.floracatalana.util.NOMENCLATURE
import com.floracatalana.floracatalana.util.TAXONOMY
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import com.google.android.gms.maps.model.UrlTileProvider
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.TileOverlay
import com.google.maps.android.compose.rememberCameraPositionState
import java.net.MalformedURLException
import java.net.URL
import kotlin.reflect.full.memberProperties

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SpeciesDetailScreen(
    state: SpeciesDetailState,
    navController: NavController,
    onEvent: (SpeciesDetailEvent) -> Unit
) {
    val species = state.species
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
                    Text(text = species.rank, color = MaterialTheme.colorScheme.tertiary)
                    Text(
                        text = species.nameLatin,
                        style = MaterialTheme.typography.displaySmall,
                        textAlign = TextAlign.Center
                    )
                    Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                        SuggestionChip(
                            onClick = { /* navController.navigate(Screen.DetailGenus.passId(species.shortGenus.code)) */ },
                            label = {
                                Text(text = "Gènere")
                            }
                        )
                        SuggestionChip(
                            onClick = { /* navController.navigate(Screen.DetailFamily.passId(species.shortFamily.code)) */ },
                            enabled = false,
                            label = {
                                Text(text = "Família")
                            }
                        )
                    }
                }
                Spacer(modifier = Modifier.height(10.dp))
                LazyRow {
                    items(items = species.categoryImages) { image ->
                        ImageCard(
                            imageUrl = image.url,
                            label = image.label,
                            modifier = Modifier.size(127.dp)
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                    }
                }
                Row(modifier = Modifier.fillMaxWidth()) {
                    Spacer(modifier = Modifier.weight(1f))
                    TextButton(
                        onClick = { /* navController.navigate(Screen.DetailImages.route) */ },
                        enabled = false
                    ) {
                        Text(text = "Més imatges (${species.images.size})")
                    }
                }
                if (species.subspecies.isNotEmpty()) {
                    Column {
                        Text(text = "Subtàxons", style = MaterialTheme.typography.titleLarge)
                        Surface(
                            color = MaterialTheme.colorScheme.tertiaryContainer,
                            shape = RoundedCornerShape(10.dp),
                            modifier = Modifier.widthIn(min = 250.dp)
                        ) {
                            Column(modifier = Modifier.padding(12.dp)) {
                                species.subspecies.forEach { subspecies ->
                                    Text(
                                        text = subspecies.code,
                                        textDecoration = TextDecoration.Underline,
                                        fontWeight = FontWeight.Bold,
                                        color = MaterialTheme.colorScheme.primary,
                                        modifier = Modifier
                                            .padding(vertical = 8.dp)
                                            .clickable {
                                                navController.navigate(
                                                    Screen.DetailSpecies.passId(subspecies.code)
                                                )
                                            }
                                    )
                                }
                            }
                        }
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                }

                val tabTitles = mutableListOf<String>()
                if (species.description != null) tabTitles.add(DESCRIPTION)
                if (species.distribution != null) tabTitles.add(DISTRIBUTION)
                if (species.nomenclature != null) tabTitles.add(NOMENCLATURE)
                if (species.taxonomy != null) tabTitles.add(TAXONOMY)
                if (species.flowering != null) tabTitles.add(FLOWERING)
                if (species.ecology != null) tabTitles.add(ECOLOGY)

                if (tabTitles.isNotEmpty()) {
                    SecondaryScrollableTabRow(selectedTabIndex = state.selectedTab) {
                        tabTitles.forEachIndexed { index, title ->
                            Tab(
                                selected = state.selectedTab == index,
                                onClick = { onEvent(SpeciesDetailEvent.SelectTab(index)) },
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
                        species.description?.let { DescriptionSection(description = it) }
                    }
                    if (tabTitles[state.selectedTab] == DISTRIBUTION) {
                        species.distribution?.let { DistributionSection(distribution = it, species.gbifId) }
                    }
                    if (tabTitles[state.selectedTab] == NOMENCLATURE) {
                        species.nomenclature?.let { NomenclatureSection(nomenclature = it) }
                    }
                    if (tabTitles[state.selectedTab] == TAXONOMY) {
                        species.taxonomy?.let {
                            TaxonomySection(
                                taxonomy = it,
                                shortFamily = species.shortFamily,
                                shortGenus = species.shortGenus,
                                nameLatin = species.nameLatin
                            )
                        }
                    }
                    if (tabTitles[state.selectedTab] == FLOWERING) {
                        species.flowering?.let { FloweringSection(flowering = it) }
                    }
                    if (tabTitles[state.selectedTab] == ECOLOGY) {
                        species.ecology?.let { EcologySection(ecology = it) }
                    }
                }
            }
        }
    }
}

@Composable
fun DescriptionSection(description: Description) {
    Column(verticalArrangement = Arrangement.spacedBy(12.dp), modifier = Modifier.padding(8.dp)) {
        description.lifeForm?.let {
            Column(modifier = Modifier.padding(horizontal = 12.dp)) {
                Text(text = "Forma vital", style = MaterialTheme.typography.titleLarge)
                Text(text = it)
            }
        }
        if (description.size.minSize != null || description.size.maxSize != null) {
            Column {
                Text(
                    text = "Mida",
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.padding(start = 12.dp)
                )
                Surface(
                    color = MaterialTheme.colorScheme.secondaryContainer,
                    shape = RoundedCornerShape(10.dp)
                ) {
                    Column(modifier = Modifier.padding(12.dp)) {
                        description.size.minSize?.let { Text(text = "Mida mínima: $it") }
                        description.size.maxSize?.let { Text(text = "Mida màxima: $it") }
                    }
                }
            }
        }
    }
}

@Composable
fun DistributionSection(distribution: String, taxonKey: Int?) {
    val uriHandler = LocalUriHandler.current
    Column(verticalArrangement = Arrangement.spacedBy(12.dp), modifier = Modifier.padding(8.dp)) {
        val cameraPositionState = rememberCameraPositionState {
            position = CameraPosition(
                LatLngBounds(
                    LatLng(40.493716, 0.039045),
                    LatLng(42.847716, 3.400021)
                ).center, 7f, 0f, 0f
            )
        }
        val uiSettings = MapUiSettings(
            compassEnabled = false,
            myLocationButtonEnabled = false,
            mapToolbarEnabled = false,
            tiltGesturesEnabled = false,
            zoomControlsEnabled = false,
            scrollGesturesEnabled = false,
            rotationGesturesEnabled = false,
            scrollGesturesEnabledDuringRotateOrZoom = false,
            zoomGesturesEnabled = false
        )
        GoogleMap(
            modifier = Modifier
                .height(300.dp)
                .border(
                    width = 1.dp,
                    color = MaterialTheme.colorScheme.primary,
                    shape = RoundedCornerShape(10.dp)
                )
                .clip(RoundedCornerShape(10.dp)),
            cameraPositionState = cameraPositionState,
            uiSettings = uiSettings
        ) {
            taxonKey?.let { TileOverlay(MapTileProvider(taxonKey = it)) }
        }

        Text(
            text = "Mapa de distribució (BDBC)",
            textDecoration = TextDecoration.Underline,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.clickable { uriHandler.openUri(distribution) }
        )

    }
}

@Composable
fun NomenclatureSection(nomenclature: Nomenclature) {
    Column(verticalArrangement = Arrangement.spacedBy(12.dp), modifier = Modifier.padding(8.dp)) {
        nomenclature.scientificName?.let {
            Column(modifier = Modifier.padding(horizontal = 12.dp)) {
                Text(text = "Nom científic - autor", style = MaterialTheme.typography.titleLarge)
                Text(text = it)
            }
        }
        if (nomenclature.synonyms.isNotEmpty()) {
            Column {
                Text(
                    text = "Sinònims",
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.padding(start = 12.dp)
                )
                Surface(
                    color = MaterialTheme.colorScheme.secondaryContainer,
                    shape = RoundedCornerShape(10.dp)
                ) {
                    Column(modifier = Modifier.padding(12.dp)) {
                        nomenclature.synonyms.forEach { synonym ->
                            Text(text = synonym)
                        }
                    }
                }
            }
        }
        if (nomenclature.catalanNames.isNotEmpty() ||
            nomenclature.spanishNames.isNotEmpty() ||
            nomenclature.occitanNames.isNotEmpty() ||
            nomenclature.frenchNames.isNotEmpty() ||
            nomenclature.englishNames.isNotEmpty()
        ) {
            Column {
                Text(
                    text = "Noms comuns",
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.padding(start = 12.dp)
                )
                Surface(
                    color = MaterialTheme.colorScheme.primaryContainer,
                    shape = RoundedCornerShape(10.dp)
                ) {
                    Column(
                        verticalArrangement = Arrangement.spacedBy(10.dp),
                        modifier = Modifier.padding(12.dp)
                    ) {
                        if (nomenclature.catalanNames.isNotEmpty()) {
                            Column {
                                Text(
                                    text = "Nom català",
                                    style = MaterialTheme.typography.titleMedium
                                )
                                Text(text = nomenclature.catalanNames.joinToString(separator = ", "))
                                nomenclature.termcatUrl?.let {
                                    val uriHandler = LocalUriHandler.current
                                    Text(
                                        text = "TERMCAT. Centre de terminologia de la llengua catalana",
                                        modifier = Modifier.clickable { uriHandler.openUri(it) }
                                    )
                                }
                            }
                        }
                        if (nomenclature.spanishNames.isNotEmpty()) {
                            Column {
                                Text(
                                    text = "Nom castellà",
                                    style = MaterialTheme.typography.titleMedium
                                )
                                Text(text = nomenclature.spanishNames.joinToString(separator = ", "))
                            }
                        }
                        if (nomenclature.occitanNames.isNotEmpty()) {
                            Column {
                                Text(
                                    text = "Nom occità",
                                    style = MaterialTheme.typography.titleMedium
                                )
                                Text(text = nomenclature.occitanNames.joinToString(separator = ", "))
                            }
                        }
                        if (nomenclature.frenchNames.isNotEmpty()) {
                            Column {
                                Text(
                                    text = "Nom francès",
                                    style = MaterialTheme.typography.titleMedium
                                )
                                Text(text = nomenclature.frenchNames.joinToString(separator = ", "))
                            }
                        }
                        if (nomenclature.englishNames.isNotEmpty()) {
                            Column {
                                Text(
                                    text = "Nom anglès",
                                    style = MaterialTheme.typography.titleMedium
                                )
                                Text(text = nomenclature.englishNames.joinToString(separator = ", "))
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun TaxonomySection(
    shortFamily: ShortTaxon,
    shortGenus: ShortTaxon,
    taxonomy: Taxonomy,
    nameLatin: String
) {
    Column(verticalArrangement = Arrangement.spacedBy(12.dp), modifier = Modifier.padding(8.dp)) {
        Column {
            Text(
                text = "Flora dels Països Catalans",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(start = 12.dp)
            )
            Surface(
                color = MaterialTheme.colorScheme.primaryContainer,
                shape = RoundedCornerShape(10.dp)
            ) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(10.dp),
                    modifier = Modifier.padding(12.dp)
                ) {
                    Text(text = "Família ${shortFamily.name}")
                    Text(text = "Gènere ${shortGenus.name}")
                    Text(text = nameLatin)
                }
            }
        }
        Column {
            Text(
                text = "Sistema APG",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(start = 12.dp)
            )
            Surface(
                color = MaterialTheme.colorScheme.primaryContainer,
                shape = RoundedCornerShape(10.dp)
            ) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(10.dp),
                    modifier = Modifier.padding(12.dp)
                ) {
                    taxonomy.apgOrder?.let { Text(text = "Ordre $it") }
                    taxonomy.apgFamily?.let { Text(text = "Família $it") }
                    taxonomy.apgGenus?.let { Text(text = "Gènere $it") }
                    taxonomy.tplName?.let { Text(text = it) }
                }
            }
        }
    }
}

@Composable
fun FloweringSection(flowering: Flowering) {
    Column(verticalArrangement = Arrangement.spacedBy(12.dp), modifier = Modifier.padding(8.dp)) {
        Flowering::class.memberProperties.forEach { member ->
            val name = member.name
            val value = member.get(flowering) as Boolean
            val month = MONTHS.entries.first { it.name.lowercase() == name.lowercase() }
            month.isFlowering = value
        }
        Surface(
            modifier = Modifier.border(BorderStroke(3.dp, color = Color(0xFF85929e))),
            shape = RoundedCornerShape(10.dp)
        ) {
            Column(modifier = Modifier.padding(12.dp)) {
                Text(text = "Hivern", style = MaterialTheme.typography.titleLarge)
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    val containerColor = Color(0xFFd6dbdf)
                    Surface(
                        modifier = Modifier
                            .size(100.dp)
                            .alpha(if (MONTHS.DECEMBER.isFlowering) 1f else 0.5f),
                        shape = RoundedCornerShape(10.dp),
                        color = if (MONTHS.DECEMBER.isFlowering) containerColor else Color.LightGray
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            Text(text = MONTHS.DECEMBER.nameCatalan)
                        }
                    }
                    Surface(
                        modifier = Modifier
                            .size(100.dp)
                            .alpha(if (MONTHS.JANUARY.isFlowering) 1f else 0.5f),
                        shape = RoundedCornerShape(10.dp),
                        color = if (MONTHS.JANUARY.isFlowering) containerColor else Color.LightGray
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            Text(text = MONTHS.JANUARY.nameCatalan)
                        }
                    }
                    Surface(
                        modifier = Modifier
                            .size(100.dp)
                            .alpha(if (MONTHS.FEBRUARY.isFlowering) 1f else 0.5f),
                        shape = RoundedCornerShape(10.dp),
                        color = if (MONTHS.FEBRUARY.isFlowering) containerColor else Color.LightGray
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            Text(text = MONTHS.FEBRUARY.nameCatalan)
                        }
                    }
                }
            }
        }
        Surface(
            modifier = Modifier.border(BorderStroke(3.dp, color = Color(0xFF7dcea0))),
            shape = RoundedCornerShape(10.dp)
        ) {
            Column(modifier = Modifier.padding(12.dp)) {
                Text(text = "Primavera", style = MaterialTheme.typography.titleLarge)
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    val containerColor = Color(0xFFd4efdf)
                    Surface(
                        modifier = Modifier
                            .size(100.dp)
                            .alpha(if (MONTHS.MARCH.isFlowering) 1f else 0.5f),
                        shape = RoundedCornerShape(10.dp),
                        color = if (MONTHS.MARCH.isFlowering) containerColor else Color.LightGray
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            Text(text = MONTHS.MARCH.nameCatalan)
                        }
                    }
                    Surface(
                        modifier = Modifier
                            .size(100.dp)
                            .alpha(if (MONTHS.APRIL.isFlowering) 1f else 0.5f),
                        shape = RoundedCornerShape(10.dp),
                        color = if (MONTHS.APRIL.isFlowering) containerColor else Color.LightGray
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            Text(text = MONTHS.APRIL.nameCatalan)
                        }
                    }
                    Surface(
                        modifier = Modifier
                            .size(100.dp)
                            .alpha(if (MONTHS.MAY.isFlowering) 1f else 0.5f),
                        shape = RoundedCornerShape(10.dp),
                        color = if (MONTHS.MAY.isFlowering) containerColor else Color.LightGray
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            Text(text = MONTHS.MAY.nameCatalan)
                        }
                    }
                }
            }
        }

        Surface(
            modifier = Modifier.border(BorderStroke(3.dp, color = Color(0xFFf7dc6f))),
            shape = RoundedCornerShape(10.dp)
        ) {
            Column(modifier = Modifier.padding(12.dp)) {
                Text(text = "Estiu", style = MaterialTheme.typography.titleLarge)
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    val containerColor = Color(0xFFfcf3cf)
                    Surface(
                        modifier = Modifier
                            .size(100.dp)
                            .alpha(if (MONTHS.JUNE.isFlowering) 1f else 0.5f),
                        shape = RoundedCornerShape(10.dp),
                        color = if (MONTHS.JUNE.isFlowering) containerColor else Color.LightGray
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            Text(text = MONTHS.JUNE.nameCatalan)
                        }
                    }
                    Surface(
                        modifier = Modifier
                            .size(100.dp)
                            .alpha(if (MONTHS.JULY.isFlowering) 1f else 0.5f),
                        shape = RoundedCornerShape(10.dp),
                        color = if (MONTHS.JULY.isFlowering) containerColor else Color.LightGray
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            Text(text = MONTHS.JULY.nameCatalan)
                        }
                    }
                    Surface(
                        modifier = Modifier
                            .size(100.dp)
                            .alpha(if (MONTHS.AUGUST.isFlowering) 1f else 0.5f),
                        shape = RoundedCornerShape(10.dp),
                        color = if (MONTHS.AUGUST.isFlowering) containerColor else Color.LightGray
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            Text(text = MONTHS.AUGUST.nameCatalan)
                        }
                    }
                }
            }
        }

        Surface(
            modifier = Modifier.border(BorderStroke(3.dp, color = Color(0xFFe59866))),
            shape = RoundedCornerShape(10.dp)
        ) {
            Column(modifier = Modifier.padding(12.dp)) {
                Text(text = "Tardor", style = MaterialTheme.typography.titleLarge)
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    val containerColor = Color(0xFFf6ddcc)
                    Surface(
                        modifier = Modifier
                            .size(100.dp)
                            .alpha(if (MONTHS.SEPTEMBER.isFlowering) 1f else 0.5f),
                        shape = RoundedCornerShape(10.dp),
                        color = if (MONTHS.SEPTEMBER.isFlowering) containerColor else Color.LightGray
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            Text(text = MONTHS.SEPTEMBER.nameCatalan)
                        }
                    }
                    Surface(
                        modifier = Modifier
                            .size(100.dp)
                            .alpha(if (MONTHS.OCTOBER.isFlowering) 1f else 0.5f),
                        shape = RoundedCornerShape(10.dp),
                        color = if (MONTHS.OCTOBER.isFlowering) containerColor else Color.LightGray
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            Text(text = MONTHS.OCTOBER.nameCatalan)
                        }
                    }
                    Surface(
                        modifier = Modifier
                            .size(100.dp)
                            .alpha(if (MONTHS.NOVEMBER.isFlowering) 1f else 0.5f),
                        shape = RoundedCornerShape(10.dp),
                        color = if (MONTHS.NOVEMBER.isFlowering) containerColor else Color.LightGray
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            Text(text = MONTHS.NOVEMBER.nameCatalan)
                        }
                    }
                }
            }
        }
    }
}

enum class MONTHS(
    val nameCatalan: String,
    var isFlowering: Boolean = false
) {
    DECEMBER(nameCatalan = "Desembre"),
    JANUARY(nameCatalan = "Gener"),
    FEBRUARY(nameCatalan = "Febrer"),
    MARCH(nameCatalan = "Març"),
    APRIL(nameCatalan = "Abril"),
    MAY(nameCatalan = "Maig"),
    JUNE(nameCatalan = "Juny"),
    JULY(nameCatalan = "Juliol"),
    AUGUST(nameCatalan = "Agost"),
    SEPTEMBER(nameCatalan = "Setembre"),
    OCTOBER(nameCatalan = "Octubre"),
    NOVEMBER(nameCatalan = "Novembre")
}

@Composable
fun EcologySection(ecology: Ecology) {
    Column(verticalArrangement = Arrangement.spacedBy(12.dp), modifier = Modifier.padding(8.dp)) {
        ecology.frequency?.let {
            Column(modifier = Modifier.padding(12.dp)) {
                Text(text = "Freqüència", style = MaterialTheme.typography.titleLarge)
                Text(text = it)
            }
        }
        ecology.habitat?.let {
            Column(modifier = Modifier.padding(12.dp)) {
                Text(text = "Hàbitat", style = MaterialTheme.typography.titleLarge)
                Text(text = it)
            }
        }
        if (
            ecology.territory.distribucioGeneral != null ||
            ecology.territory.fisiograficCatalunya != null ||
            ecology.territory.zonesFitogeografiques != null
        ) {
            Column {
                Text(
                    text = "Territori",
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.padding(start = 12.dp)
                )
                Surface(
                    color = MaterialTheme.colorScheme.primaryContainer,
                    shape = RoundedCornerShape(10.dp)
                ) {
                    Column(
                        verticalArrangement = Arrangement.spacedBy(10.dp),
                        modifier = Modifier.padding(12.dp)
                    ) {
                        ecology.territory.fisiograficCatalunya?.let {
                            Text(text = "Territori fisiogràfic a Catalunya: $it")
                        }
                        ecology.territory.zonesFitogeografiques?.let {
                            Text(text = "Zones fitogeogràfiques: $it")
                        }
                        ecology.territory.distribucioGeneral?.let {
                            Text(text = "Àrea de distribució general: $it")
                        }
                    }
                }
            }
        }
        val isAltitudeNull = ecology.altitude.altitudMinimaInferior == null &&
                ecology.altitude.altitudMinima == null &&
                ecology.altitude.altitudMaxima == null &&
                ecology.altitude.altitudMaximaSuperior == null

        if (!isAltitudeNull) {
            Column {
                Text(
                    text = "Altitud",
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.padding(start = 12.dp)
                )
                Surface(
                    color = MaterialTheme.colorScheme.secondaryContainer,
                    shape = RoundedCornerShape(10.dp)
                ) {
                    Column(
                        verticalArrangement = Arrangement.spacedBy(10.dp),
                        modifier = Modifier.padding(12.dp)
                    ) {
                        ecology.altitude.altitudMinimaInferior?.let {
                            Text(text = "Altitud mínima inferior (m): $it")
                        }
                        ecology.altitude.altitudMinima?.let {
                            Text(text = "Altitud mínima (m): $it")
                        }
                        ecology.altitude.altitudMaxima?.let {
                            Text(text = "Altitud màxima (m): $it")
                        }
                        ecology.altitude.altitudMaximaSuperior?.let {
                            Text(text = "Altitud màxima superior (m): $it")
                        }
                    }
                }
            }
        }
        ecology.phytosociology?.let {
            Column(modifier = Modifier.padding(12.dp)) {
                Text(
                    text = "Adscripció fitosociològica",
                    style = MaterialTheme.typography.titleLarge
                )
                Text(text = it)
            }
        }
    }
}

@Preview(widthDp = 127, heightDp = 165)
@Composable
fun ImageCard(
    modifier: Modifier = Modifier,
    imageUrl: String? = "",
    label: String? = "",
    onClick: () -> Unit = {}
) {
    Column(modifier = modifier.clickable { onClick() }) {
        Box(
            modifier = Modifier
                .weight(1f)
                .clip(
                    shape = RoundedCornerShape(
                        topStart = 10.dp,
                        bottomEnd = 10.dp,
                        bottomStart = 10.dp
                    )
                )
                .background(MaterialTheme.colorScheme.surfaceVariant)
                .fillMaxSize()
        ) {
            if (imageUrl != null) {
                val painter = rememberAsyncImagePainter(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(imageUrl)
                        .size(150, 150)
                        .build()
                )
                when (painter.state) {
                    AsyncImagePainter.State.Empty -> {}
                    is AsyncImagePainter.State.Error -> {
                        Icon(
                            modifier = Modifier
                                .size(24.dp)
                                .align(Alignment.Center),
                            imageVector = Icons.Outlined.CloudOff,
                            contentDescription = "Error",
                            tint = MaterialTheme.colorScheme.outline
                        )
                    }

                    is AsyncImagePainter.State.Loading -> {
                        Column(
                            modifier = Modifier.fillMaxSize(),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            CircularProgressIndicator()
                        }
                    }

                    is AsyncImagePainter.State.Success -> {
                        Image(
                            modifier = Modifier.fillMaxSize(),
                            painter = painter,
                            contentDescription = null,
                            contentScale = ContentScale.Crop
                        )
                    }
                }
            } else {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Icon(
                        modifier = Modifier.size(50.dp),
                        imageVector = Icons.Outlined.FilterVintage,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
            }

            label?.let {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            brush = Brush.verticalGradient(
                                colors = listOf(
                                    Color.Transparent, Color.Black.copy(alpha = 0.4f)
                                ),
                                startY = 200f
                            )
                        )
                )
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(12.dp),
                    contentAlignment = Alignment.BottomStart
                ) {
                    Text(
                        text = label,
                        style = MaterialTheme.typography.titleSmall,
                        color = Color.White,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
        }
    }
}

class MapTileProvider(val taxonKey: Int) : UrlTileProvider(512, 512) {
    override fun getTileUrl(
        x: Int,
        y: Int,
        zoom: Int
    ): URL? {
        return try {
            URL(HttpRoutes.GbifTileOverlay(taxonKey = taxonKey, x = x, y = y, zoom = zoom))
        } catch (e: MalformedURLException) {
            throw AssertionError(e)
        }
    }
}