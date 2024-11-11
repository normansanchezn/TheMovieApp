package com.example.themovieapp.presentation.customview

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.themovieapp.R

class ItemMovieView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    private val movieImage: ImageView
    private val movieTitle: TextView

    init {
        LayoutInflater.from(context).inflate(R.layout.item_movie_view, this, true)

        movieImage = findViewById(R.id.movie_image)
        movieTitle = findViewById(R.id.movie_title)
    }

    fun bind(movieTitleText: String, movieImageUrl: String) {
        movieTitle.text = movieTitleText

        Glide.with(context)
            .load(movieImageUrl)
            .into(movieImage)
    }
}