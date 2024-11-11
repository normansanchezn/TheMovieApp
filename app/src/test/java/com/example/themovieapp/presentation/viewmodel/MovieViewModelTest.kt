package com.example.themovieapp.presentation.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.themovieapp.data.model.MovieEntity
import com.example.themovieapp.data.repository.MovieRepository
import com.example.themovieapp.presentation.ui.viewmodel.MovieViewModel
import junit.framework.Assert.assertNotNull
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.kotlin.whenever

class MovieViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private lateinit var repository: MovieRepository
    private lateinit var viewModel: MovieViewModel
    private val testDispatcher = StandardTestDispatcher()

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setUp() {
        repository = mock(MovieRepository::class.java)
        Dispatchers.setMain(testDispatcher)
        viewModel = MovieViewModel(repository)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `state is MoviesLoaded when repository returns data`() = runBlocking {
        val sampleMovies = listOf(MovieEntity(1, "Movie 1", "path1"))

        whenever(repository.getLocalMovies()).thenReturn(sampleMovies)

        viewModel.loadMovies()
        testDispatcher.scheduler.advanceUntilIdle()

        assertNotNull(viewModel.state.value)
    }
}