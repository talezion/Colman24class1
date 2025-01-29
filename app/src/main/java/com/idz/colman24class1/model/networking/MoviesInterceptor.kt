package com.idz.colman24class1.model.networking

import com.idz.colman24class1.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

class MoviesInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
            .addHeader("Authorization", "Bearer ${BuildConfig.TMDB_ACCESS_TOKEN}")
            .addHeader("accept", "application/json")
            .build()
        return chain.proceed(request)
    }
}
