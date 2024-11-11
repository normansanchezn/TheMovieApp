package com.example.themovieapp.presentation.ui.intent

sealed class MovieIntent {
    data object LoadMovies: MovieIntent()
    data object ReloadMovies: MovieIntent()
}