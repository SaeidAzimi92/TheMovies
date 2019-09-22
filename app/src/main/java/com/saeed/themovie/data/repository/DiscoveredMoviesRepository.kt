package com.saeed.themovie.data.repository

import androidx.lifecycle.MutableLiveData
import com.saeed.themovie.data.models.MoviesList

class DiscoveredMoviesRepository : BaseRepository() {

    fun discoveredMovies(
        page: Int, startDate: String, endDate: String
    ): MutableLiveData<MoviesList> {
        return callService(
            services.discoveredMovies(
                API_KEY,
                page,
                startDate,
                endDate
            )
        )
    }
}