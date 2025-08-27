package com.floracatalana.floracatalana.di

import com.floracatalana.floracatalana.data.remote.FloracatalanaApi
import com.floracatalana.floracatalana.data.remote.FloracatalanaApiImpl
import org.koin.core.annotation.Factory
import org.koin.core.annotation.Module

@Module
class AppModule {

    @Factory(binds = [FloracatalanaApi::class])
    fun floracatalanaApi(): FloracatalanaApi = FloracatalanaApiImpl()
}