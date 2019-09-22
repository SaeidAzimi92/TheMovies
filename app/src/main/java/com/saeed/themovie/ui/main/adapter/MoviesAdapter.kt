package com.saeed.themovie.ui.main.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.saeed.themovie.R
import com.saeed.themovie.data.models.Movie
import com.saeed.themovie.utils.ImageLoader
import kotlinx.android.synthetic.main.adapter_movie_item.view.*

class MoviesAdapter(var onClick: (Int) -> Unit) : RecyclerView.Adapter<MovieItemVH>() {

    private val items: MutableList<Movie> by lazy { mutableListOf<Movie>() }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieItemVH {
        return MovieItemVH(
            LayoutInflater.from(parent.context).inflate(
                R.layout.adapter_movie_item, parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: MovieItemVH, position: Int) {
        holder.bindTo(items[position])
        holder.itemView.setOnClickListener {
            onClick(items[position].id)
        }
    }

    override fun getItemCount() = items.size

    fun appendItem(items: MutableList<Movie>) {
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    fun clear() {
        items.clear()
        notifyDataSetChanged()
    }
}

class MovieItemVH(private var view: View) : RecyclerView.ViewHolder(view) {

    @SuppressLint("SetTextI18n")
    fun bindTo(data: Movie) {
        view.tvTitle.text = data.title
        ImageLoader.load(data.posterPath, view.imgMovie)
    }
}