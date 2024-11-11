package com.example.themovieapp.presentation.ui.states

import com.example.themovieapp.data.model.MovieEntity

data class MovieState(
    val isLoading: Boolean = false,
    val movies: List<MovieEntity> = emptyList(),
    val errorMessage: String? = null
)