package com.saeed.themovie.data.repository

import androidx.lifecycle.MutableLiveData
import com.saeed.themovie.data.models.MoviesList

class MoviesRepository : BaseRepository() {

    fun getTopRatedMovies(page: Int): MutableLiveData<MoviesList> {
        return callService(services.getTopRatedMovies(API_KEY, page))
    }
}