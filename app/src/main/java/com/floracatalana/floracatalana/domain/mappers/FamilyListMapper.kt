package com.floracatalana.floracatalana.domain.mappers

import com.fleeksoft.ksoup.Ksoup
import com.floracatalana.floracatalana.data.remote.HttpRoutes
import com.floracatalana.floracatalana.data.remote.dto.FamilyListResponse
import com.floracatalana.floracatalana.domain.model.Family

fun FamilyListResponse.toFamily(): Family {
    val nomFamilia = Ksoup.parse(html = nom_familia_cat).text()
    val familyUrl = Ksoup.parse(html = nom_familia_cat).body().select("a").attr("href")
    
    return Family(
        code = codi_familia,
        nameCat = nomFamilia,
        nameLatin = nom_familia_llati,
        url = HttpRoutes.BASE_URL + familyUrl,
//        nGenera = TODO(),
//        nodeId = TODO(),
//        rank = TODO(),
//        nSpecies = TODO(),
//        genera = TODO()
    )
}