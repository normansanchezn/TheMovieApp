package com.example.themovieapp.presentation.ui

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.themovieapp.data.model.MovieEntity
import com.example.themovieapp.databinding.ActivityMainBinding
import com.example.themovieapp.presentation.ui.adapter.MovieAdapter
import com.example.themovieapp.presentation.ui.intent.MovieIntent
import com.example.themovieapp.presentation.ui.viewmodel.MovieViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val movieViewModel: MovieViewModel by viewModel()
    private val movieAdapter: MovieAdapter by lazy { MovieAdapter() }
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupView()
        observeViewModel()

        movieViewModel.onIntent(MovieIntent.LoadMovies)
    }

    private fun observeViewModel() {
        movieViewModel.state.observe(this) { state ->
            if (state.isLoading) {
                showLoading()
            } else {
                hideLoading()
            }

            if (state.movies.isNotEmpty()) {
                showMovies(state.movies)
            }

            state.errorMessage?.let { message ->
                showErrorMessage(message)
            }
        }
    }

    private fun showErrorMessage(message: String) {

    }

    private fun showMovies(movies: List<MovieEntity>) {

    }

    private fun hideLoading() {

    }

    private fun showLoading() {

    }

    private fun setupView() {
        binding.moviesRV.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = movieAdapter
        }
    }
}