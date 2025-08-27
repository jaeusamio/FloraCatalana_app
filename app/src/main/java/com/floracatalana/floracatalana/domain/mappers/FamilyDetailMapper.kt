package com.floracatalana.floracatalana.domain.mappers

import com.floracatalana.floracatalana.data.remote.HttpRoutes
import com.floracatalana.floracatalana.data.remote.dto.FamilyDetailResponse
import com.floracatalana.floracatalana.domain.model.Family

fun FamilyDetailResponse.toFamily(): Family {
    val nodeId = (nid.firstOrNull()?.value ?: 0).toString()
    val url = "${HttpRoutes.BASE_URL}/flora/node/$nodeId"

    return Family(
        code = field_codi2.firstOrNull()?.value ?: "",
        nameCat = field_nom_de_la_familia1.firstOrNull()?.value ?: "",
        nameLatin = field_pronunciaci.firstOrNull()?.value ?: "",
        nodeId = nodeId,
        rank = "FAMíLIA",
        url = url,
//        nGenera = TODO(),
//        nSpecies = TODO(),
//        genera = TODO()
    )
}