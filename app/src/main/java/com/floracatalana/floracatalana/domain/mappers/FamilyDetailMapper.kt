package com.floracatalana.floracatalana.domain.mappers

import com.floracatalana.floracatalana.data.remote.HttpRoutes
import com.floracatalana.floracatalana.data.remote.dto.FamilyDetailResponse
import com.floracatalana.floracatalana.domain.model.Family
import com.floracatalana.floracatalana.domain.model.species.CategoryImage
import com.floracatalana.floracatalana.domain.model.species.Description
import com.floracatalana.floracatalana.domain.model.species.DescriptionSection
import com.floracatalana.floracatalana.domain.model.species.Ecology
import com.floracatalana.floracatalana.domain.model.species.Territory

fun FamilyDetailResponse.toFamily(): Family {
    val nodeId = (nid.firstOrNull()?.value ?: 0).toString()
    val url = "${HttpRoutes.BASE_URL}/flora/node/$nodeId"

    val categoryImages = listOfNotNull(
        field_txtimatge1.firstOrNull()?.let {
            CategoryImage(
                label = it.value,
                url = field_imatge1.firstOrNull()?.url?.replace("http://", "https://www.")
            )
        },
        field_txtimatge2.firstOrNull()?.let {
            CategoryImage(
                label = it.value,
                url = field_imatge2.firstOrNull()?.url?.replace("http://", "https://www.")
            )
        },
        field_txtimatge3.firstOrNull()?.let {
            CategoryImage(
                label = it.value,
                url = field_imatge3.firstOrNull()?.url?.replace("http://", "https://www.")
            )
        },
        field_txtimatge4.firstOrNull()?.let {
            CategoryImage(
                label = it.value,
                url = field_imatge4.firstOrNull()?.url?.replace("http://", "https://www.")
            )
        },
        field_txtimatge5.firstOrNull()?.let {
            CategoryImage(
                label = it.value,
                url = field_imatge5.firstOrNull()?.url?.replace("http://", "https://www.")
            )
        },
        field_txtimatge6.firstOrNull()?.let {
            CategoryImage(
                label = it.value,
                url = field_imgfam6.firstOrNull()?.url?.replace("http://", "https://www.")
            )
        },
    )

    val descriptionSections = listOfNotNull(
        field_subterranifam.firstOrNull()?.let {
            DescriptionSection(
                title = "Subterrani",
                text = it.value
            )
        },
        field_portfam.firstOrNull()?.let {
            DescriptionSection(
                title = "Port",
                text = it.value
            )
        },
        field_tija_troncfam.firstOrNull()?.let {
            DescriptionSection(
                title = "Tija/tronc",
                text = it.value
            )
        },
        field_fullesfam.firstOrNull()?.let {
            DescriptionSection(
                title = "Fulles",
                text = it.value
            )
        },
        field_flor_inflorescenciafam.firstOrNull()?.let {
            DescriptionSection(
                title = "Flor/inflorescencia",
                text = it.value
            )
        },
        field_fruit_llavorfam.firstOrNull()?.let {
            DescriptionSection(
                title = "Fruit/llavor",
                text = it.value
            )
        }
    )

    val territory = Territory(
        distribucioGeneral = field_distribucio.firstOrNull()?.value,
    )
    val ecology = Ecology(
        habitat = field_habitatfam.firstOrNull()?.value,
        territory = territory
    )

    return Family(
        code = field_codi2.firstOrNull()?.value ?: "",
        nameCat = field_nom_de_la_familia1.firstOrNull()?.value ?: "",
        nameLatin = field_pronunciaci.firstOrNull()?.value ?: "",
        nodeId = nodeId,
        url = url,
        ecology = ecology,
        description = Description(sections = descriptionSections),
        categoryImages = categoryImages,
        usos = field_usos.firstOrNull()?.value,
    )
}