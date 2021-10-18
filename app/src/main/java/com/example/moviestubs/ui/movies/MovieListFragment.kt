package com.example.moviestubs.ui.movies

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moviestubs.R
import com.example.moviestubs.databinding.MovieListFragmentBinding
import com.example.moviestubs.repository.MovieFilter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieListFragment : Fragment() {

    /**
     * Lazily initialize our [MovieListViewModel].
     */
    private val viewModel: MovieListViewModel by lazy {
        ViewModelProvider(this).get(MovieListViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = MovieListFragmentBinding.inflate(inflater)

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

        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.overfllow_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        viewModel.updateFilter(
            when (item.itemId) {
                R.id.show_all_menu -> MovieFilter.SHOW_ALL
                R.id.show_top_10_menu -> MovieFilter.TOP_10
                else -> MovieFilter.TOP_10
            }
        )
        return true
    }
}