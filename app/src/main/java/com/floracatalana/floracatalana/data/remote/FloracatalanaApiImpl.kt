package com.floracatalana.floracatalana.data.remote

import com.floracatalana.floracatalana.BuildConfig
import com.floracatalana.floracatalana.data.remote.dto.FamilyDetailResponse
import com.floracatalana.floracatalana.data.remote.dto.FamilyListResponse
import com.floracatalana.floracatalana.data.remote.dto.GenusDetailResponse
import com.floracatalana.floracatalana.data.remote.dto.GenusListResponse
import com.floracatalana.floracatalana.data.remote.dto.SpeciesDetailResponse
import com.floracatalana.floracatalana.data.remote.dto.SpeciesListResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.auth.Auth
import io.ktor.client.plugins.auth.providers.BasicAuthCredentials
import io.ktor.client.plugins.auth.providers.basic
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.url
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

class FloracatalanaApiImpl : FloracatalanaApi {

    private val httpClient = HttpClient {
        install(ContentNegotiation) { json(Json { ignoreUnknownKeys = true }) }
        install(Auth) {
            basic {
                credentials {
                    BasicAuthCredentials(
                        username = BuildConfig.apiUser,
                        password = BuildConfig.apiPassword
                    )
                }
            }
        }
    }

    override suspend fun getSpeciesList(
        page: Int,
        searchValue: String,
        genusCode: String?,
        familyCode: String?
    ): List<SpeciesListResponse> {
        return httpClient.get {
            url(HttpRoutes.SPECIES_LIST)
            parameter("page", page)
            parameter("field_nom_cientific_value", searchValue)
            parameter("field_codi4_value", genusCode)
            parameter("field_codi2_value", familyCode)
        }.body()
    }

    override suspend fun getGenusList(page: Int, familyCode: String?): List<GenusListResponse> {
        return httpClient.get {
            url(HttpRoutes.GENUS_LIST)
            parameter("page", page)
            parameter("field_codi2_value", familyCode)
        }.body()
    }

    override suspend fun getFamilyList(page: Int): List<FamilyListResponse> {
        return httpClient.get {
            url(HttpRoutes.FAMILY_LIST)
            parameter("page", page)
        }.body()
    }

    override suspend fun getSpeciesDetail(code: String): SpeciesDetailResponse {
        return httpClient.get {
            url(HttpRoutes.SPECIES_DETAIL.passCode(code))
        }.body()
    }

    override suspend fun getGenusDetail(code: String): GenusDetailResponse {
        return httpClient.get {
            url(HttpRoutes.GENUS_DETAIL.passCode(code))
        }.body()
    }

    override suspend fun getFamilyDetail(code: String): FamilyDetailResponse {
        return httpClient.get {
            url(HttpRoutes.FAMILY_DETAIL.passCode(code))
        }.body()
    }
}