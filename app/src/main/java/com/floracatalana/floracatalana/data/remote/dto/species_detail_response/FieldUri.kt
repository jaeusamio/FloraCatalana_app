package com.floracatalana.floracatalana.data.remote.dto.species_detail_response

import kotlinx.serialization.Serializable

@Serializable
data class FieldUri(
    val options: List<String>, // FIXME: Check if type is String
    val title: String,
    val uri: String
)
