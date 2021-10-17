package com.example.moviestubs.ui.moviedetail

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.moviestubs.R
import com.example.moviestubs.databinding.MovieDetailFragmentBinding
import com.example.moviestubs.databinding.MovieListFragmentBinding
import com.example.moviestubs.ui.movies.MovieListViewModel

class MovieDetailFragment : Fragment() {

    private lateinit var viewModel: MovieDetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = MovieDetailFragmentBinding.inflate(inflater)
        val selectedMovie = MovieDetailFragmentArgs.fromBundle(requireArguments()).selectedMovie
        val detailViewModelFactory = MovieDetailViewModelFactory(selectedMovie)
        binding.movieDetailViewModel = ViewModelProvider(this, detailViewModelFactory)
            .get(MovieDetailViewModel::class.java)
        binding.movieName.text = selectedMovie.name
        return binding.root
    }
}