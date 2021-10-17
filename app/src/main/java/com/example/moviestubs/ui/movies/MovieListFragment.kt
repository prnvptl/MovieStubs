package com.example.moviestubs.ui.movies

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moviestubs.databinding.MovieListFragmentBinding

class MovieListFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = MovieListFragmentBinding.inflate(inflater)
        val viewModel: MovieListViewModel by viewModels()

        // Giving the binding access to the MovieListViewModel
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        val adapter = MovieListAdapter(MovieListAdapter.OnClickListener { movie ->
            viewModel.displayMovieDetails(movie)
        })

        binding.recyclerView.adapter = adapter
        binding.recyclerView.addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.VERTICAL))

        viewModel.navigateToSelectedProperty.observe(viewLifecycleOwner, Observer { movie ->
            if(movie != null) {
                findNavController().navigate(MovieListFragmentDirections.actionMovieListFragmentToMovieDetailFragment(movie))
                viewModel.displayMovieDetailsComplete()
            }
        })

        return binding.root
    }
}