package com.example.moviestubs.ui.movies

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.moviestubs.R
import com.example.moviestubs.model.Movie

class MovieListAdapter : RecyclerView.Adapter<MovieListAdapter.ViewHolder>() {
    var data = listOf<Movie>()
    set(value) {
        field = value
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val movieName: TextView = itemView.findViewById(R.id.movie_name)
        val movieDuration: TextView = itemView.findViewById(R.id.movie_duration)
        val movieDirector: TextView = itemView.findViewById(R.id.movie_director)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.movie_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data[position]
        holder.movieName.text = item.name
        holder.movieDuration.text = item.duration
        holder.movieDirector.text = item.director
    }

    override fun getItemCount(): Int {
        return data.size
    }
}