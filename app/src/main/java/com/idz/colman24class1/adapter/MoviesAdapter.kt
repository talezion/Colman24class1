package com.idz.colman24class1.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.idz.colman24class1.databinding.MovieCellItemBinding
import com.idz.colman24class1.model.Movies

class MoviesAdapter(
    var movies: Movies?
): RecyclerView.Adapter<MovieViewHolder>() {

    override fun getItemCount(): Int =
        movies?.results?.count() ?: 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding = MovieCellItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = movies?.results?.get(position)
        holder.bind(movie)
    }
}