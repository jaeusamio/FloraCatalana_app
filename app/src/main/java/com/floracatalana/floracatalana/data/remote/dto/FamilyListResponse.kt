package com.floracatalana.floracatalana.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class FamilyListResponse(
    val id: String,
    val nom_cat: String,
    val nom_llati: String,
    val llista_generes: String,
    val llista_taxons_finals: String
)