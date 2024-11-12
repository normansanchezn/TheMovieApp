package com.example.themovieapp.di

import androidx.room.Room
import com.example.themovieapp.data.api.MoviesApi
import com.example.themovieapp.data.database.AppDatabase
import com.example.themovieapp.data.network.HeaderInterceptor
import com.example.themovieapp.data.repository.MovieRepository
import com.example.themovieapp.presentation.ui.viewmodel.MovieViewModel
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val DATA_BASE_NAME = "movie_database"

val appModule = module {

    single {
        Room.databaseBuilder(
            androidApplication(),
            AppDatabase::class.java, DATA_BASE_NAME
        ).build()
    }

    single {
        Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .client(OkHttpClient.Builder().addInterceptor(
                HeaderInterceptor()
            ).build())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    single {
        get<Retrofit>().create(MoviesApi::class.java)
    }

    single { get<AppDatabase>().movieDao() }

    single { MovieRepository(get(), get()) }

    single { MovieViewModel(get()) }
}