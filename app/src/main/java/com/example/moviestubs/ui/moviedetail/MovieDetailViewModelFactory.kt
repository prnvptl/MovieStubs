package com.example.moviestubs.ui.moviedetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.moviestubs.model.Movie

/**
 * Overrides the create function to ensure that the selected movie is available for the [MovieDetailViewModel].
 * For Reference: https://developer.android.com/codelabs/kotlin-android-training-view-model#7
 */
class MovieDetailViewModelFactory (private val selectedMovie: Movie) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MovieDetailViewModel::class.java)) {
            return MovieDetailViewModel(selectedMovie) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}