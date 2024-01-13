package com.floracatalana.floracatalana.di

import android.content.Context
import com.floracatalana.floracatalana.data.repository.SpeciesRepositoryImpl
import com.floracatalana.floracatalana.domain.repository.SpeciesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun speciesRepository(@ApplicationContext context: Context): SpeciesRepository = SpeciesRepositoryImpl(context)
}