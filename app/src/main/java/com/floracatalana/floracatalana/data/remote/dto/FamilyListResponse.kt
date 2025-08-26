package com.floracatalana.floracatalana.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class FamilyListResponse(
    val codi_familia: String,
    val llista_generes: String,
    val llista_taxons_finals: String,
    val nom_familia_cat: String,
    val nom_familia_llati: String
)