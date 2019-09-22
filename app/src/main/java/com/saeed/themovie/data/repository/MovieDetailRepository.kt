package com.saeed.themovie.data.repository

import androidx.lifecycle.MutableLiveData
import com.saeed.themovie.data.models.MovieDetail

class MovieDetailRepository : BaseRepository() {

    fun getMovieDetail(id: Int): MutableLiveData<MovieDetail> {
        return callService(services.getMovieDetail(id, API_KEY))
    }
}