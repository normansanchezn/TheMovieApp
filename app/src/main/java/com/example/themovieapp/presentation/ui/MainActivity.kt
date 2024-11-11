package com.example.themovieapp.presentation.ui

import android.content.Context
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.themovieapp.R
import com.example.themovieapp.data.model.MovieEntity
import com.example.themovieapp.databinding.ActivityMainBinding
import com.example.themovieapp.presentation.extensions.gone
import com.example.themovieapp.presentation.extensions.visible
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
                showErrorMessage(
                    context = this,
                    message = message,
                    onRetry = {
                        movieViewModel.onIntent(MovieIntent.ReloadMovies)
                    }
                )
            }
        }
    }

    private fun showErrorMessage(context: Context, message: String, onRetry: () -> Unit) {
        val dialog = AlertDialog.Builder(context)
            .setTitle(getString(R.string.error_title))
            .setMessage(message)
            .setCancelable(false)
            .setPositiveButton(getString(R.string.try_again)) { _, _ ->
                onRetry()
            }
            .setNegativeButton(getString(R.string.cancel)) { dialogInterface, _ ->
                dialogInterface.dismiss()
            }
            .create()
        dialog.show()
    }

    private fun showMovies(movies: List<MovieEntity>) {
        movieAdapter.setListOfMovies(movies)
    }

    private fun hideLoading() {
        binding.loadingProgressBar.gone()
    }

    private fun showLoading() {
        binding.loadingProgressBar.visible()
    }

    private fun setupView() {
        binding.moviesRv.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = movieAdapter
        }
    }
}