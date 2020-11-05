package com.example.perfmov

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.films_list.view.*

class MovieAdapter(val movies: List<Movies>) : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(
                LayoutInflater.from(parent.context)
                        .inflate(R.layout.films_list, parent, false)
        )
    }

    override fun getItemCount() = movies.size

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = movies[position]
        holder.view.tltFilm.text = movie.title
        holder.view.rtFilm.text = movie.rating
        holder.view.dtFilm.text = movie.date

    }

    class MovieViewHolder(val view: View) : RecyclerView.ViewHolder(view)
}