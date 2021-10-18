package com.example.moviestubs.ui.movies

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviestubs.model.Movie
import com.example.moviestubs.repository.MovieFilter
import com.example.moviestubs.repository.MovieRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class MovieListViewModel @Inject constructor(
    private val movieRepository: MovieRepositoryImpl
) : ViewModel() {

    private val _movies = MutableLiveData<List<Movie>>()

    val movies : LiveData<List<Movie>>
        get() = _movies

    // Internally, we use a MutableLiveData to handle navigation to the selected property
    private val _navigateToSelectedProperty = MutableLiveData<Movie?>()

    // The external immutable LiveData for the navigation property
    val navigateToSelectedProperty: MutableLiveData<Movie?>
        get() = _navigateToSelectedProperty

    init {
        getTopMovies()
    }

    private fun getTopMovies() {
        viewModelScope.launch {
            try {
                val topMovies = movieRepository.getMoviesByRank(1, 10)
                val ids = topMovies.map { movie -> movie.id }
                Log.i("MovieListViewModel", "Success: All movies $topMovies")
                _movies.value = movieRepository.getMovieDetails(ids)
            } catch (e: Exception) {
                Log.e("MovieListViewModel", "Error fetching All Movies " + e.message.toString())
            }
        }
    }

    private fun getAllMovies() {
        viewModelScope.launch {
            try {
                val allMovies = movieRepository.getMovies()
                Log.i("MovieListViewModel", "Success: Top Movies $allMovies")
                _movies.value = allMovies
            } catch (e: Exception) {
                Log.e("MovieListViewModel", "Error fetching All Movies " + e.message.toString())
            }
        }
    }

    fun displayMovieDetails(selectedMovie: Movie) {
        _navigateToSelectedProperty.value = selectedMovie
    }

    fun displayMovieDetailsComplete() {
        _navigateToSelectedProperty.value = null
    }

    fun updateFilter(filter: MovieFilter) {
        when(filter) {
            MovieFilter.TOP_10 -> getTopMovies()
            MovieFilter.SHOW_ALL -> getAllMovies()
        }
    }
}