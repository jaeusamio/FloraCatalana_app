package com.floracatalana.floracatalana.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class SpeciesListResponse(
    val Imatges: String,
    val codi_familia: String,
    val codi_genere: String,
    val codi_taxon_final: String,
    val nom_cientific: String,
    val nom_familia: String,
    val nom_genere: String
)