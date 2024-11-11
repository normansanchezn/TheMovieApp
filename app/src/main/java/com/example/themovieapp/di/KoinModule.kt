package com.example.themovieapp.di

import androidx.room.Room
import com.example.themovieapp.data.api.MoviesApi
import com.example.themovieapp.data.database.AppDatabase
import com.example.themovieapp.data.repository.MovieRepository
import com.example.themovieapp.presentation.ui.viewmodel.MovieViewModel
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val appModule = module {

    single {
        Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .client(OkHttpClient.Builder().build())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    single {
        get<Retrofit>().create(MoviesApi::class.java)
    }

    single {
        Room.databaseBuilder(
            androidApplication(),
            AppDatabase::class.java, "movie_database"
        ).build()
    }

    single { get<AppDatabase>().movieDao() }

    single { MovieRepository(get(), get()) }
}

val viewModelModule = module {
    viewModel { MovieViewModel(get()) }
}