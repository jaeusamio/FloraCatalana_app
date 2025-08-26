package com.floracatalana.floracatalana.data.remote.dto.species_detail_response

import kotlinx.serialization.Serializable

@Serializable
data class FieldProcessed(
    val format: String,
    val processed: String,
    val value: String
)
