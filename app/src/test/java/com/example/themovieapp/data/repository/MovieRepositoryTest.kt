package com.example.themovieapp.data.repository

import com.example.themovieapp.data.api.MoviesApi
import com.example.themovieapp.data.database.MovieDao
import com.example.themovieapp.data.model.MovieEntity
import junit.framework.Assert.assertNotNull
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.mockito.kotlin.whenever

class MovieRepositoryTest {

    private lateinit var movieDao: MovieDao
    private lateinit var moviesApi: MoviesApi
    private lateinit var repository: MovieRepository

    @Before
    fun setUp() {
        movieDao = mock(MovieDao::class.java)
        moviesApi = mock(MoviesApi::class.java)
        repository = MovieRepository(
            moviesApi = moviesApi,
            movieDao = movieDao
        )
    }

    @Test
    fun `fetch movies from database if available`(): Unit = runBlocking {
        val sampleMovies = listOf(MovieEntity(1, "Movie 1", "path1"))

        whenever(movieDao.getAllMovies()).thenReturn(sampleMovies)

        val result = repository.getLocalMovies()

        assertNotNull(result)
        verify(movieDao).getAllMovies()
    }

    @Test
    fun `insert movies into database`() = runBlocking {
        val sampleMovies = listOf(MovieEntity(1, "Movie 1", "path1"))

        repository.saveMovies(sampleMovies)

        verify(movieDao).insertMovies(sampleMovies)
    }
}