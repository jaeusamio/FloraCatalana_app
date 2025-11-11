package com.floracatalana.floracatalana.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class SpeciesListResponse(
    val id: String,
    val id_genere: String,
    val id_familia: String,
    val imatges: String,
    val nom_cientific: String,
    val nom_genere: String,
    val nom_familia: String,
)