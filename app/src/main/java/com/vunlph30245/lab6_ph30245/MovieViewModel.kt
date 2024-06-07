package com.vunlph30245.lab6_ph30245

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vunlph30245.lab6_ph30245.APIManager.MovieResponse
import com.vunlph30245.lab6_ph30245.APIManager.RetrofitService
import com.vunlph30245.lab6_ph30245.APIManager.toMovie
import kotlinx.coroutines.launch

class MovieViewModel : ViewModel() {
    private val _movies = MutableLiveData<List<Movie>>()
    val movies: LiveData<List<Movie>> = _movies

    init {
        getMovies()
    }

    private fun getMovies() {
        viewModelScope.launch {
            try {
                val response = RetrofitService().movieService.getListFilms()
                if (response.isSuccessful) {
                    val movieList = response.body()?.map { it.toMovie() }
                    _movies.postValue(movieList)
                } else {
                    Log.e("MovieViewModel", "getMovies: Response was not successful")
                    _movies.postValue(emptyList())
                }
            } catch (e: Exception) {
                Log.e("MovieViewModel", "getMovies: ${e.message}")
                _movies.postValue(emptyList())
            }
        }
    }

    fun getMovieById(filmId: String?): LiveData<Movie?> {
        val liveData = MutableLiveData<Movie?>()
        filmId?.let {
            viewModelScope.launch {
                try {
                    val response = RetrofitService().movieService.getFilmDetail(filmId)
                    if (response.isSuccessful) {
                        liveData.postValue(response.body()?.toMovie())
                    } else {
                        liveData.postValue(null)
                    }
                } catch (e: Exception) {
                    liveData.postValue(null)
                }
            }
        }
        return liveData
    }

    private val _isSuccess = MutableLiveData<Boolean>()
    val isSuccess: LiveData<Boolean> = _isSuccess
    fun addFilm(movieRequest: MovieRequest) {
        viewModelScope.launch {
            _isSuccess.value = try {
                val response = RetrofitService().movieService.addFilm(movieRequest)
                if (response.isSuccessful) {
                    response.body()?.let {
                        if (it.status == 1) {
                            getMovies()
                            true
                        } else {
                            false
                        }
                    } ?: false
                } else {
                    false
                }
            } catch (e: Exception) {
                false
            }
        }
    }

    fun updateMovie(movieRequest: MovieRequest) {
        viewModelScope.launch {
            _isSuccess.value = try {
                val response = RetrofitService().movieService.updateFilm(movieRequest)
                if (response.isSuccessful) {
                    response.body()?.let {
                        if (it.status == 1) {
                            getMovies()
                            true
                        } else {
                            false
                        }
                    } ?: false
                } else {
                    false
                }
            } catch (e: Exception) {
                false
            }
        }
    }

//    fun deleteMovieById(id: String) {
//        viewModelScope.launch {
//            _isSuccess.value = try {
//                val response = RetrofitService().movieService.deleteFilm(id)
//                if (response.isSuccessful) {
//                    response.body()?.let {if (it.status == 1) {
//                        getMovies()
//                        true
//                    } else {
//                        false
//                    }
//                    } ?: false
//                } else {
//                    false
//                }
//            } catch (e: Exception) {
//                false
//            }
//        }
//    }
}
