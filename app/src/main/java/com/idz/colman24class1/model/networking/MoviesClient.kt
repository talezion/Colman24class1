package com.idz.colman24class1.model.networking

import com.idz.colman24class1.BuildConfig
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object MoviesClient {

    private val okHttpClient: OkHttpClient by lazy {
        OkHttpClient.Builder()
            .addInterceptor(MoviesInterceptor())
            .build()
    }

    val moviesApiClient: MoviesAPI by lazy {
        val retrofitClient = Retrofit.Builder()
            .baseUrl(BuildConfig.TMDB_BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        retrofitClient.create(MoviesAPI::class.java)
    }
}