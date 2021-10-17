package com.example.moviestubs.ui.movies

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moviestubs.R
import com.example.moviestubs.databinding.MovieListFragmentBinding

class MovieListFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = MovieListFragmentBinding.inflate(inflater)
        val model: MovieListViewModel by viewModels()
        val adapter = MovieListAdapter(MovieListAdapter.OnClickListener { movie ->
            model.displayMovieDetails(movie)
        })
        binding.recyclerView.adapter = adapter
        binding.recyclerView.addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.VERTICAL))
        model.movies.observe(this, { movies ->
            adapter.submitList(movies)
        })
        model.navigateToSelectedProperty.observe(this, Observer { movie ->
            if(movie != null) {
                findNavController().navigate(MovieListFragmentDirections.actionMovieListFragmentToMovieDetailFragment(movie))
                model.displayMovieDetailsComplete()
            }

        })
        return binding.root
    }
}