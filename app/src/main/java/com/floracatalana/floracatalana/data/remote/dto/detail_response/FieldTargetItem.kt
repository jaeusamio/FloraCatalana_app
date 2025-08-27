package com.floracatalana.floracatalana.data.remote.dto.detail_response

import kotlinx.serialization.Serializable

@Serializable
data class FieldTargetItem(
    val target_id: Int,
    val target_type: String,
    val target_uuid: String,
    val url: String
)
