package com.example.themovieapp.data.api

import com.example.themovieapp.data.model.MovieResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesApi {

    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("language") language: String = "en-US"
    ): Response<MovieResponse>

}