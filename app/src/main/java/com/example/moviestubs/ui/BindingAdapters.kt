package com.example.moviestubs.ui

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.moviestubs.R
import com.example.moviestubs.model.Movie
import com.example.moviestubs.ui.movies.MovieListAdapter
import com.bumptech.glide.load.model.LazyHeaders
import com.bumptech.glide.load.model.GlideUrl


/**
 * When there is no movie property data (data is null), hide the [RecyclerView], otherwise show it.
 */
@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<Movie>?) {
    val adapter = recyclerView.adapter as MovieListAdapter
    adapter.submitList(data)
}

/**
 * Uses the Glide library to load an image it's id into an [ImageView]
 */
@BindingAdapter("imageUrlId")
fun bindImage(imgView: ImageView, imageUrlId: Int?) {
    imageUrlId?.let {
        val url = GlideUrl(
            "https://via.placeholder.com/3500x3500.png?text=MovieId:$imageUrlId", LazyHeaders.Builder()
                .addHeader("User-Agent", "your-user-agent")
                .build()
        )
        Glide.with(imgView.context)
            .load(url)
            .apply(RequestOptions()
                .placeholder(R.drawable.loading_animation)
                .error(R.drawable.ic_broken_image))
            .into(imgView)
    }
}