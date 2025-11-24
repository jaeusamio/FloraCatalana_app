package com.floracatalana.floracatalana.data.paging.factory

import androidx.paging.Pager
import com.floracatalana.floracatalana.data.remote.dto.SpeciesListResponse

fun interface SpeciesPagerFactory {
    fun create(
        searchValue: String,
        genusCode: String?,
        familyCode: String?
    ): Pager<Int, SpeciesListResponse>
}