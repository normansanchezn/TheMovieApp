package com.example.themovieapp.data.mappers

import com.example.themovieapp.data.model.MovieDTO
import com.example.themovieapp.data.model.MovieEntity

fun MovieDTO.toMovieEntity(): MovieEntity {
    return MovieEntity(
        id = this.id,
        title = this.title,
        posterPath = this.posterPath
    )
}