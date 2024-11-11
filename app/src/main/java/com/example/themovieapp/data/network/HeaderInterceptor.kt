package com.example.themovieapp.data.network

import okhttp3.Interceptor
import okhttp3.Response

class HeaderInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()

        val newRequest = originalRequest.newBuilder()
            .header("Authorization", "Bearer 07f5ea02fd3b8c37e33ed145e738c258")
            .header("Content-Type", "application/json")
            .build()

        return chain.proceed(newRequest)
    }
}