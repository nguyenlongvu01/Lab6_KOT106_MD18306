package com.vunlph30245.lab6_ph30245.APIManager

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

open class RetrofitService() {
    private val BASE_URL = ""

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val movieService: MovieService by lazy {
        retrofit.create(MovieService::class.java)
    }
}
