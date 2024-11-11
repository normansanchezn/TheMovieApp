package com.example.themovieapp.di

import android.content.Context
import androidx.room.Room
import com.example.themovieapp.data.database.AppDatabase
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

fun provideRetrofit(): Retrofit {
    return Retrofit.Builder()
        .baseUrl("https://api.themoviedb.org/3/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}

fun provideDataBase(context: Context): AppDatabase {
    return Room.databaseBuilder(context, AppDatabase::class.java, "movie_database")
        .fallbackToDestructiveMigration()
        .build()
}