package com.example.themovieapp.data.repository

import com.example.themovieapp.data.api.MoviesApi
import com.example.themovieapp.data.database.MovieDao
import com.example.themovieapp.data.model.MovieEntity
import com.example.themovieapp.data.model.MovieResponse
import retrofit2.Response

class MovieRepository(
    private val moviesApi: MoviesApi,
    private val movieDao: MovieDao
) {

    suspend fun getLocalMovies(): List<MovieEntity> {
        return movieDao.getAllMovies()
    }

    suspend fun fetchMoviesFromApi(): Response<MovieResponse> {
        return moviesApi.getPopularMovies()
    }

    suspend fun saveMovies(movies: List<MovieEntity>) {
        movieDao.insertMovies(movies)
    }
}