package com.vunlph30245.lab6_ph30245.APIManager

import com.vunlph30245.lab6_ph30245.MovieRequest
import com.vunlph30245.lab6_ph30245.StatusResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface MovieService {
    @GET("list-film.php")
    suspend fun getListFilms(): Response<List<MovieResponse>>
    @GET("film-detail.php")
    suspend fun getFilmDetail(@Query("id") id: String): Response<MovieResponse>

    @POST("add-film.php")
    suspend fun addFilm(@Body filmRequest: MovieRequest): Response<StatusResponse>
    @POST("update-film.php")
    suspend fun updateFilm(@Body filmRequest: MovieRequest): Response<StatusResponse>
}