package com.floracatalana.floracatalana.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class GenusListResponse(
    val `codi-familia`: String,
    val codi_genere: String,
    val nom_familia: String,
    val nom_genere: String,
    val taxons_finals: String
)