package com.example.themovieapp

import android.app.Application
import com.example.themovieapp.di.appModule
import com.example.themovieapp.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class TheMovieApp: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@TheMovieApp)
            modules(appModule, viewModelModule)
        }
    }
}