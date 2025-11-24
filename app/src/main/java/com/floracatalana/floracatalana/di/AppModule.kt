package com.floracatalana.floracatalana.di

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.floracatalana.floracatalana.data.paging.factory.GenusPagerFactory
import com.floracatalana.floracatalana.data.paging.factory.SpeciesPagerFactory
import com.floracatalana.floracatalana.data.paging.paging_source.FamilyPagingSource
import com.floracatalana.floracatalana.data.paging.paging_source.GenusPagingSource
import com.floracatalana.floracatalana.data.paging.paging_source.SpeciesPagingSource
import com.floracatalana.floracatalana.data.remote.FloracatalanaApi
import com.floracatalana.floracatalana.data.remote.FloracatalanaApiImpl
import com.floracatalana.floracatalana.data.remote.dto.FamilyListResponse
import org.koin.core.annotation.Factory
import org.koin.core.annotation.Module

@Module
class AppModule {

    @Factory(binds = [FloracatalanaApi::class])
    fun floracatalanaApi(): FloracatalanaApi = FloracatalanaApiImpl()

    @Factory
    fun speciesPagerFactory(api: FloracatalanaApi): SpeciesPagerFactory {
        return SpeciesPagerFactory { searchValue, genusCode, familyCode ->
            Pager(
                config = PagingConfig(pageSize = 50),
                pagingSourceFactory = {
                    SpeciesPagingSource(
                        api = api,
                        searchValue = searchValue,
                        genusCode = genusCode,
                        familyCode = familyCode
                    )
                }
            )
        }
    }

    @Factory
    fun genusPagerFactory(api: FloracatalanaApi): GenusPagerFactory {
        return GenusPagerFactory { familyCode ->
            Pager(
                config = PagingConfig(pageSize = 50),
                pagingSourceFactory = {
                    GenusPagingSource(
                        api = api,
                        familyCode = familyCode
                    )
                }
            )
        }
    }

    @Factory
    fun familyPager(api: FloracatalanaApi): Pager<Int, FamilyListResponse> {
        return Pager(
            config = PagingConfig(pageSize = 50),
            pagingSourceFactory = { FamilyPagingSource(api = api) }
        )
    }
}