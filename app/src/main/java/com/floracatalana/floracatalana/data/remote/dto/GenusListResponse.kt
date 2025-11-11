package com.floracatalana.floracatalana.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class GenusListResponse(
    val id: String,
    val id_familia: String,
    val nom_cat: String,
    val nom_familia_cat: String,
    val llista_taxons_finals: String
)