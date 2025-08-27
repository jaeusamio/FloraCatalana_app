package com.floracatalana.floracatalana.data.remote.dto.detail_response

import kotlinx.serialization.Serializable

@Serializable
data class Path(
    val alias: String?,
    val langcode: String,
    val pid: Int?
)