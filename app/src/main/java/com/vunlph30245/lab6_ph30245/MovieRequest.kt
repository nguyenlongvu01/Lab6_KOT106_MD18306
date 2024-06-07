package com.vunlph30245.lab6_ph30245

data class MovieRequest(
    val filmId: Int? = null,
    val filmName: String,
    val duration: Int,
    val releaseDate: String,
    val genre: String,
    val national: String,
    val description: String,
    val image: String
)
data class StatusResponse(
    val status: Int,
    val message: String
)