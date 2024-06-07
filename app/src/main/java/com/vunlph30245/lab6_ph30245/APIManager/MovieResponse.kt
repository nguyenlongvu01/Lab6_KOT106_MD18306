package com.vunlph30245.lab6_ph30245.APIManager

import com.google.gson.annotations.SerializedName

data class MovieResponse(
    @SerializedName("filmId") val filmId: String,
    @SerializedName("filmName") val filmName: String,
    @SerializedName("duration") val duration: String,
    @SerializedName("releaseDate") val releaseDate: String,
    @SerializedName("genre") val genre: String,
    @SerializedName("national") val national: String,
    @SerializedName("description") val description: String,
    @SerializedName("image") val image: String,
)

