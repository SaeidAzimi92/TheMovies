package com.saeed.themovie

import android.app.Application
import com.saeed.themovie.di.repositoryModels
import com.saeed.themovie.di.viewModels
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@App)
            modules(listOf(viewModels, repositoryModels))
        }
    }
}