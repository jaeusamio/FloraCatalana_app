package com.floracatalana.floracatalana.data.remote.dto.detail_response

import kotlinx.serialization.Serializable

@Serializable
data class FieldImatge(
    val alt: String,
    val height: Int,
    val target_id: Int,
    val target_type: String,
    val target_uuid: String,
    val title: String,
    val url: String,
    val width: Int
)