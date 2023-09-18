package com.raisproject.movieapp

import android.app.Application
import com.raisproject.core.di.databaseModule
import com.raisproject.core.di.networkModule
import com.raisproject.core.di.repositoryModule
import com.raisproject.movieapp.di.useCaseModule
import com.raisproject.movieapp.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
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