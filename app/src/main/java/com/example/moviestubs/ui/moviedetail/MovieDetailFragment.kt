package com.example.moviestubs.ui.moviedetail

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.moviestubs.databinding.MovieDetailFragmentBinding
import android.content.Intent
import android.net.Uri

private const val ZOC_DOC_BASE_URL = "https://www.zocdoc.com/"

class MovieDetailFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = MovieDetailFragmentBinding.inflate(inflater)
        val selectedMovie = MovieDetailFragmentArgs.fromBundle(requireArguments()).selectedMovie
        val detailViewModelFactory = MovieDetailViewModelFactory(selectedMovie)
        binding.movieDetailViewModel = ViewModelProvider(this, detailViewModelFactory)
            .get(MovieDetailViewModel::class.java)
        binding.apply {
            movieDetailName.text = selectedMovie.name
            movieDetailActors.text = selectedMovie.actors.joinToString(", ")
            movieDetailDuration.text = selectedMovie.duration
            movieDetailDescription.text = selectedMovie.description
            // Navigates just to the zocdoc home website for now
            // TODO: Implement actual functionality
            buyTicketButton.setOnClickListener {
                val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(ZOC_DOC_BASE_URL))
                startActivity(browserIntent)
            }
        }
        return binding.root
    }
}