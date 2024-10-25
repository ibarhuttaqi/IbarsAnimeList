package com.example.ibarsanimelist

import android.app.Application
import com.example.ibarsanimelist.core.di.databaseModule
import com.example.ibarsanimelist.core.di.networkModule
import com.example.ibarsanimelist.core.di.repositoryModule
import com.example.ibarsanimelist.di.useCaseModule
import com.example.ibarsanimelist.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin
import org.koin.core.logger.Level

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@MyApplication)
            modules(
                listOf(
                    databaseModule,
                    networkModule,
                    repositoryModule,
                    useCaseModule,
                    viewModelModule
                )
            )
        }
    }
}