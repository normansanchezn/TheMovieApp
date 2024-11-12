package com.example.themovieapp.presentation.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.themovieapp.data.mappers.toMovieEntity
import com.example.themovieapp.data.repository.MovieRepository
import com.example.themovieapp.presentation.ui.intent.MovieIntent
import com.example.themovieapp.presentation.ui.states.MovieState
import kotlinx.coroutines.launch

class MovieViewModel(private val repository: MovieRepository): ViewModel() {
    private val _state = MutableLiveData<MovieState>()
    val state: LiveData<MovieState> get() = _state

    fun onIntent(intent: MovieIntent) {
        when (intent) {
            is MovieIntent.LoadMovies -> loadMovies()
            is MovieIntent.ReloadMovies -> reloadMovies()
        }
    }

    private fun reloadMovies() {
        _state.value = MovieState(isLoading = true)
        getMoviesFromApi()
    }

    fun loadMovies() {
        _state.value = MovieState(isLoading = true)

        viewModelScope.launch {
            val localMovies = repository.getLocalMovies()
            if (localMovies.isEmpty()) {
                getMoviesFromApi()
            } else {
                _state.value = MovieState(movies = localMovies)
            }
        }
    }

    private fun getMoviesFromApi() {
        viewModelScope.launch {
            val apiResponse = repository.fetchMoviesFromApi()
            if (apiResponse.isSuccessful) {
                apiResponse.body()?.results?.map { it.toMovieEntity() }?.let { movies ->
                    repository.saveMovies(movies)
                    _state.value = MovieState(movies = movies)
                } ?: showError("Failed to load data.")
            } else {
                showError("Failed to load data.")
            }
        }
    }

    private fun showError(message: String) {
        _state.value = MovieState(errorMessage = message)
    }
}