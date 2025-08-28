package com.floracatalana.floracatalana.domain.mappers

import com.floracatalana.floracatalana.data.remote.HttpRoutes
import com.floracatalana.floracatalana.data.remote.dto.FamilyDetailResponse
import com.floracatalana.floracatalana.domain.model.Family
import com.floracatalana.floracatalana.domain.model.species.Description
import com.floracatalana.floracatalana.domain.model.species.DescriptionSection

fun FamilyDetailResponse.toFamily(): Family {
    val nodeId = (nid.firstOrNull()?.value ?: 0).toString()
    val url = "${HttpRoutes.BASE_URL}/flora/node/$nodeId"

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
    return Family(
        code = field_codi2.firstOrNull()?.value ?: "",
        nameCat = field_nom_de_la_familia1.firstOrNull()?.value ?: "",
        nameLatin = field_pronunciaci.firstOrNull()?.value ?: "",
        nodeId = nodeId,
        url = url,
        description = Description(sections = descriptionSections),
    )
}