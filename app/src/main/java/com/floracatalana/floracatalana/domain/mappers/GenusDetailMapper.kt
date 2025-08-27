package com.floracatalana.floracatalana.domain.mappers

import com.floracatalana.floracatalana.data.remote.HttpRoutes
import com.floracatalana.floracatalana.data.remote.dto.GenusDetailResponse
import com.floracatalana.floracatalana.domain.model.Genus

fun GenusDetailResponse.toGenus(): Genus {
    val nodeId = (nid.firstOrNull()?.value ?: 0).toString()
    val url = "${HttpRoutes.BASE_URL}/flora/node/$nodeId"

    return Genus(
        code = field_codi4.firstOrNull()?.value ?: "",
        nameLatin = field_nom_del_genere1.firstOrNull()?.value ?: "",
        nodeId = nodeId,
        rank = "GÈNERE",
        url = url,
//        nameCat = TODO(),
//        shortFamily = TODO(),
//        nSpecies = TODO(),
//        species = TODO()
    )
}