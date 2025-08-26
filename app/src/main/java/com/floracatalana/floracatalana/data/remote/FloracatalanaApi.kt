package com.floracatalana.floracatalana.data.remote

import com.floracatalana.floracatalana.data.remote.dto.FamilyListResponse
import com.floracatalana.floracatalana.data.remote.dto.GenusListResponse
import com.floracatalana.floracatalana.data.remote.dto.SpeciesDetailResponse
import com.floracatalana.floracatalana.data.remote.dto.SpeciesListResponse

interface FloracatalanaApi {

    suspend fun getSpeciesList(page: Int = 0, genusCode: String? = null, familyCode: String? = null): List<SpeciesListResponse>

    suspend fun getSearchSpeciesList(searchValue: String, page: Int = 0): List<SpeciesListResponse>

    suspend fun getGenusList(page: Int = 0, familyCode: String): List<GenusListResponse>

    suspend fun getFamilyList(page: Int = 0): List<FamilyListResponse>

    suspend fun getSpeciesDetail(code: String): SpeciesDetailResponse
}