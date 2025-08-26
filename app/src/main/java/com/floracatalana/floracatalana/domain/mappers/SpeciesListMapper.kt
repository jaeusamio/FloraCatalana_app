package com.floracatalana.floracatalana.domain.mappers

import com.fleeksoft.ksoup.Ksoup
import com.floracatalana.floracatalana.data.remote.HttpRoutes
import com.floracatalana.floracatalana.data.remote.dto.SpeciesListResponse
import com.floracatalana.floracatalana.domain.model.ShortTaxon
import com.floracatalana.floracatalana.domain.model.species.Species

fun SpeciesListResponse.toSpecies(): Species {
    val nomCientific = Ksoup.parse(html = nom_cientific).text()
    val nomFamilia = Ksoup.parse(html = nom_familia).text()
    val nomGenere = Ksoup.parse(html = nom_genere).text()
    val speciesUrl = Ksoup.parse(html = nom_cientific).body().select("a").attr("href")
    return Species(
        code = codi_taxon_final,
        nameLatin = nomCientific,
        shortFamily = ShortTaxon(
            code = codi_familia,
            name = nomFamilia
        ),
        shortGenus = ShortTaxon(
            code = codi_genere,
            name = nomGenere
        ),
        url = HttpRoutes.BASE_URL + speciesUrl
    )
}