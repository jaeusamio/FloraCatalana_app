package com.floracatalana.floracatalana.domain.mappers

import com.fleeksoft.ksoup.Ksoup
import com.floracatalana.floracatalana.data.remote.HttpRoutes
import com.floracatalana.floracatalana.data.remote.dto.SpeciesDetailResponse
import com.floracatalana.floracatalana.domain.model.TaxonRank
import com.floracatalana.floracatalana.domain.model.species.Altitude
import com.floracatalana.floracatalana.domain.model.species.CategoryImage
import com.floracatalana.floracatalana.domain.model.species.Description
import com.floracatalana.floracatalana.domain.model.species.Ecology
import com.floracatalana.floracatalana.domain.model.species.Flowering
import com.floracatalana.floracatalana.domain.model.species.Nomenclature
import com.floracatalana.floracatalana.domain.model.species.Size
import com.floracatalana.floracatalana.domain.model.species.Species
import com.floracatalana.floracatalana.domain.model.species.Territory

fun SpeciesDetailResponse.toSpecies(): Species {
    val altitude = Altitude(
        altitudMaxima = field_altitud_maxima_m_.firstOrNull()?.value,
        altitudMinima = field_altitud_minima_m_.firstOrNull()?.value,
        altitudMinimaInferior = field_altitud_m.firstOrNull()?.value,
        altitudMaximaSuperior = field_altitud_maxima_superior.firstOrNull()?.value
    )
    val territory = Territory(
        distribucioGeneral = field_distribuciogen.firstOrNull()?.value,
        fisiograficCatalunya = field_fisiofragia.firstOrNull()?.value,
        zonesFitogeografiques = field_zones_fitogeografiques.firstOrNull()?.value
    )

    val ecology = Ecology(
        altitude = altitude,
        frequency = field_frequencia.firstOrNull()?.value,
        habitat = field_habitat.firstOrNull()?.value,
        territory = territory
    )

    val flowering = Flowering(
        january = field_gener.firstOrNull()?.value != "NO",
        february = field_febrer.firstOrNull()?.value != "NO",
        march = field_mar.firstOrNull()?.value != "NO",
        april = field_abril.firstOrNull()?.value != "NO",
        may = field_floracio_maig.firstOrNull()?.value != "NO",
        june = field_floracio_juny.firstOrNull()?.value != "NO",
        july = field_floracio_juliol.firstOrNull()?.value != "NO",
        august = field_floracio_agost.firstOrNull()?.value != "NO",
        september = field_floracio_setembre.firstOrNull()?.value != "NO",
        october = field_floracio_octubre.firstOrNull()?.value != "NO",
        november = field_floracio_novembre.firstOrNull()?.value != "NO",
        december = field_floracio_desembre.firstOrNull()?.value != "NO"
    )

    val categoryImages = listOf(
        CategoryImage(
            label = "Hàbitat",
            url = field_imatge_habitat.firstOrNull()?.url?.replace("http://", "https://www.")
        ),
        CategoryImage(
            label = "Subterrani",
            url = field_imatge_subterranivasculars.firstOrNull()?.url?.replace("http://", "https://www.")
        ),
        CategoryImage(
            label = "Port",
            url = field_imatge_port.firstOrNull()?.url?.replace("http://", "https://www.")
        ),
        CategoryImage(
            label = "Tija",
            url = field_imatge_tija_troncvascul.firstOrNull()?.url?.replace("http://", "https://www.")
        ),
        CategoryImage(
            label = "Fulles",
            url = field_imatge_fullesvasculars.firstOrNull()?.url?.replace("http://", "https://www.")
        ),
        CategoryImage(
            label = "Flors",
            url = field_imatge_flor_iflorescencia.firstOrNull()?.url?.replace("http://", "https://www.")
        ),
        CategoryImage(
            label = "Fruits",
            url = field_imatge_fruit_llavors.firstOrNull()?.url?.replace("http://", "https://www.")
        ),
        CategoryImage(
            label = "Altres",
            url = field_imatge_altresvasculars.firstOrNull()?.url?.replace("http://", "https://www.")
        ),
    )

    val size = Size(
        maxSize = field_mida_maxima.firstOrNull()?.value,
        minSize = field_mida_minima.firstOrNull()?.value
    )
    val descriptionSections = listOfNotNull(
        field_subterrani_descripcio.firstOrNull()?.let {
            DescriptionSection(
                title = "Subterrani",
                text = it.value
            )
        },
        field_port_descripcio.firstOrNull()?.let {
            DescriptionSection(
                title = "Port",
                text = it.value
            )
        },
        field_tija_tronc_descripcio.firstOrNull()?.let {
            DescriptionSection(
                title = "Tija/tronc",
                text = it.value
            )
        },
        field_fulles_descripcio.firstOrNull()?.let {
            DescriptionSection(
                title = "Fulles",
                text = it.value
            )
        },
        field_flor_inflorescencia_desc.firstOrNull()?.let {
            DescriptionSection(
                title = "Flor/inflorescencia",
                text = it.value
            )
        },
        field_fruit_llavor_descripcio.firstOrNull()?.let {
            DescriptionSection(
                title = "Fruit/llavor",
                text = it.value
            )
        }
    )
    val description = Description(
        lifeForm = field_forma_vital.firstOrNull()?.value,
        size = size,
        sections = descriptionSections
    )

    val nomenclature = Nomenclature(
        catalanNames = field_nom_catala.firstOrNull()?.value?.split(", ") ?: emptyList(),
        englishNames = field_nom_angles.firstOrNull()?.value?.split(", ") ?: emptyList(),
        frenchNames = field_nom_frances.firstOrNull()?.value?.split(", ") ?: emptyList(),
        occitanNames = field_nom_occita.firstOrNull()?.value?.split(", ") ?: emptyList(),
        scientificName = field_nom_cientific_autor.firstOrNull()?.value,
        spanishNames = field_nom_castella.firstOrNull()?.value?.split(", ") ?: emptyList(),
        synonyms = field_sinonims.firstOrNull()?.value?.split(", ") ?: emptyList(),
        termcatUrl = field_termcat.firstOrNull()?.uri
    )

    val subspecies = field_taxons.map {
        val code = it.url.split("/").last()
        Species(
            code = code,
            url = HttpRoutes.BASE_URL + it.url
        )
    }

    val mapaDistProcessed = field_mapadist.firstOrNull()?.processed
    val gbifId = if (mapaDistProcessed != null) {
        Ksoup.parse(html = mapaDistProcessed).body().select("div").first()?.id()?.replace("map", "")?.toInt()
    } else null

    val rank = TaxonRank.entries.firstOrNull { it.label == field_tipus_de_fitxa1.firstOrNull()?.value }

    return Species(
        code = field_codi.firstOrNull()?.value ?: "",
        nameLatin = field_nom_cientific.firstOrNull()?.value ?: "",
        url = HttpRoutes.BASE_URL + path.firstOrNull()?.alias,
        categoryImages = categoryImages,
        distribution = field_distribucio_geografica1.firstOrNull()?.uri,
        description = description,
        ecology = ecology,
        flowering = flowering,
        nameCat = field_nom_catala.firstOrNull()?.value,
        nomenclature = nomenclature,
        rank = rank ?: TaxonRank.SPECIES,
        subspecies = subspecies,
        gbifId = gbifId
//        taxonomy = TODO(),
//        nodeId = TODO(),
//        images = TODO(),
//        shortFamily = TODO(),
//        shortGenus = TODO()
    )
}