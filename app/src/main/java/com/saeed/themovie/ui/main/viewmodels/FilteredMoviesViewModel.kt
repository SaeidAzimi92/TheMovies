package com.saeed.themovie.ui.main.viewmodels

import com.saeed.themovie.data.repository.DiscoveredMoviesRepository

class FilteredMoviesViewModel(var repository: DiscoveredMoviesRepository) :
    BaseViewModel(repository) {

    fun getDiscoveredMovies(
        page: Int, startDate: String, endDate: String
    ) = repository.discoveredMovies(page, startDate, endDate)
}
