package com.example.themovieapp.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.themovieapp.data.model.MovieEntity

@Database(entities = [MovieEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
}