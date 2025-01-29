package com.idz.colman24class1.adapter

import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.idz.colman24class1.BuildConfig
import com.idz.colman24class1.R
import com.idz.colman24class1.databinding.MovieCellItemBinding
import com.idz.colman24class1.model.Movie
import com.squareup.picasso.Picasso

class MovieViewHolder(
    binding: MovieCellItemBinding
): RecyclerView.ViewHolder(binding.root) {

    private var movie: Movie? = null
    private var nameTextView: TextView? = null
    private var movieImageView: ImageView? = null
    init {
        nameTextView = binding.textViewMovieTitle
        movieImageView = binding.movieThumbNailImageView
    }

    fun bind(movie: Movie?) {
        this.movie = movie
        nameTextView?.text = movie?.title

        movieImageView?.let {
            Picasso.get()
                .load((BuildConfig.TMDB_POSTER_BASE_URL + movie?.posterPath))
                .placeholder(R.drawable.avatar)
                .into(it)
        }
    }
}