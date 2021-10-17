package com.example.moviestubs.ui.moviedetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.moviestubs.model.Movie

class MovieDetailViewModelFactory (private val selectedMovie: Movie) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MovieDetailViewModel::class.java)) {
            return MovieDetailViewModel(selectedMovie) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}