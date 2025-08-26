package com.floracatalana.floracatalana.data.remote.dto.species_detail_response

import kotlinx.serialization.Serializable

@Serializable
data class Type(
    val target_id: String,
    val target_type: String,
    val target_uuid: String
)