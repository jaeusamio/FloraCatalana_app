package com.floracatalana.floracatalana.domain.mappers

import com.fleeksoft.ksoup.Ksoup
import com.floracatalana.floracatalana.data.remote.HttpRoutes
import com.floracatalana.floracatalana.data.remote.dto.GenusListResponse
import com.floracatalana.floracatalana.domain.model.Genus
import com.floracatalana.floracatalana.domain.model.ShortTaxon

fun GenusListResponse.toGenus(): Genus {
    val nomFamilia = Ksoup.parse(html = nom_familia).text()
    val nomGenere = Ksoup.parse(html = nom_genere).text()
    val genusUrl = Ksoup.parse(html = nomGenere).body().select("a").attr("href")

    return Genus(
        code = codi_genere,
        nameLatin = nomGenere,
        url = HttpRoutes.BASE_URL + genusUrl,
        shortFamily = ShortTaxon(
            code = `codi-familia`,
            name = nomFamilia
        ),
//        nSpecies = TODO(),
//        nameCat = TODO(),
//        nodeId = TODO(),
//        rank = TODO(),
//        species = TODO()
    )
}