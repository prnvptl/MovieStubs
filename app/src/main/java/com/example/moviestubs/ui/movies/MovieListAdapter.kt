package com.example.moviestubs.ui.movies

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.moviestubs.databinding.MovieListItemBinding
import com.example.moviestubs.model.Movie

class MovieListAdapter(private val onClickListener: OnClickListener) : ListAdapter<Movie, MovieListAdapter.ViewHolder>(MovieDiffCallBack) {

    class ViewHolder(private val binding: MovieListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Movie) {
            binding.apply {
                movieName.text = item.name
                movieDuration.text = item.duration
                movieDirector.text = item.director
                movie = item
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = MovieListItemBinding.inflate(LayoutInflater.from(parent.context))
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.itemView.setOnClickListener {
            onClickListener.onClick(item)
        }
        holder.bind(item)
    }

    companion object MovieDiffCallBack: DiffUtil.ItemCallback<Movie>() {
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem == newItem
        }
    }

    class OnClickListener(private val clickListener: (movie: Movie) -> Unit) {
        fun onClick(movie: Movie) = clickListener(movie)
    }
}
