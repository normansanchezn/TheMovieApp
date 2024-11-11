package com.example.themovieapp.presentation.ui.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.themovieapp.data.model.MovieEntity

class MovieDiffCallback : DiffUtil.ItemCallback<MovieEntity>() {

    override fun areItemsTheSame(oldItem: MovieEntity, newItem: MovieEntity): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: MovieEntity, newItem: MovieEntity): Boolean {
        return oldItem == newItem
    }
}