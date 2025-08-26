package com.floracatalana.floracatalana.data.remote.dto.species_detail_response

import kotlinx.serialization.Serializable

@Serializable
data class Timestamp(
    val format: String,
    val value: String
)