package com.floracatalana.floracatalana.domain.mappers

import com.fleeksoft.ksoup.Ksoup
import com.floracatalana.floracatalana.data.remote.HttpRoutes
import com.floracatalana.floracatalana.data.remote.dto.GenusListResponse
import com.floracatalana.floracatalana.domain.model.Genus
import com.floracatalana.floracatalana.domain.model.ShortTaxon

fun GenusListResponse.toGenus(): Genus {
    val nomFamilia = Ksoup.parse(html = nom_familia).text()
    val parse = Ksoup.parse(html = nom_genere)
    val nomGenere = parse.text()
    val genusUrl = parse.body().select("a").attr("href")
    val nodeId = genusUrl.split("/").last().replace("\\", "")

    return Genus(
        code = codi_genere,
        nameLatin = nomGenere,
        url = HttpRoutes.BASE_URL + genusUrl,
        shortFamily = ShortTaxon(
            code = `codi-familia`,
            name = nomFamilia
        ),
        nodeId = nodeId,
//        nameCat = TODO(),
//        nSpecies = TODO(),
//        rank = TODO(),
//        species = TODO()
    )
}