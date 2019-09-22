package com.saeed.themovie.ui.main.viewmodels

import com.saeed.themovie.data.repository.MovieDetailRepository

class MovieDetailViewModel(var repository: MovieDetailRepository) : BaseViewModel(repository) {

    fun getMovieDetail(id: Int) = repository.getMovieDetail(id)
}
