package com.floracatalana.floracatalana.di

import android.content.Context
import com.floracatalana.floracatalana.data.remote.FloracatalanaApi
import com.floracatalana.floracatalana.data.remote.FloracatalanaApiImpl
import com.floracatalana.floracatalana.data.repository.SpeciesRepositoryImpl
import com.floracatalana.floracatalana.domain.repository.SpeciesRepository
import org.koin.core.annotation.Factory
import org.koin.core.annotation.Module

@Module
class AppModule {

    @Factory(binds = [SpeciesRepository::class])
    fun speciesRepository(context: Context): SpeciesRepository = SpeciesRepositoryImpl(context)

    @Factory(binds = [FloracatalanaApi::class])
    fun floracatalanaApi(): FloracatalanaApi = FloracatalanaApiImpl()
}