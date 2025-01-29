package com.idz.colman24class1.model.networking

import com.idz.colman24class1.model.Movies
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesAPI {

    @GET("discover/movie")
    fun getPopularMovies(
        @Query("include_adult") includeAdults: Boolean = false,
        @Query("include_video") includeVideo: Boolean = false,
        @Query("language") language: String = "en-US",
        @Query("page") page: Int = 1,
        @Query("sort_by") sortBy: String = "popularity.desc",
    ): Call<Movies>
}
