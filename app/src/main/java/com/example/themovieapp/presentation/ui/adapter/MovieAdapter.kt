package com.example.themovieapp.presentation.ui.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.themovieapp.data.model.MovieEntity
import com.example.themovieapp.presentation.customview.ItemMovieView

class MovieAdapter: ListAdapter<MovieEntity, MovieAdapter.MovieViewHolder>(MovieDiffCallback()) {

    private var movieList = mutableListOf<MovieEntity>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val itemMovieView = ItemMovieView(parent.context)
        return MovieViewHolder(itemMovieView)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = movieList[position]
        holder.bind(movie)
    }

    fun setListOfMovies(movies: List<MovieEntity>) {
        movieList = movies.toMutableList()
    }

    inner class MovieViewHolder(private val itemMovieView: ItemMovieView) : RecyclerView.ViewHolder(itemMovieView) {
        fun bind(movie: MovieEntity) {
            itemMovieView.bind(movie.title, movie.posterPath)
        }
    }
}