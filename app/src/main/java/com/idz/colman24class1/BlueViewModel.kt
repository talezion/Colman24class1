package com.idz.colman24class1

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.idz.colman24class1.model.Model
import com.idz.colman24class1.model.Movies

class BlueViewModel: ViewModel() {
    val movies: LiveData<Movies> = Model.shared.movies

    fun fetchMovies() {
        Model.shared.getPopularMovies()
    }
}
