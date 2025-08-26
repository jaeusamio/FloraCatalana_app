package com.floracatalana.floracatalana
import android.app.Application
import com.floracatalana.floracatalana.di.AppModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.ksp.generated.defaultModule
import org.koin.ksp.generated.module

class MyApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MyApplication)
            modules(
                AppModule().module,
                defaultModule
            )
        }
    }
}