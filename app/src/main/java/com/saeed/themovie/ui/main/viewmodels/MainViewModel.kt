package com.saeed.themovie.ui.main.viewmodels

import com.saeed.themovie.data.repository.MoviesRepository

class MainViewModel (var repository: MoviesRepository) : BaseViewModel(repository) {

    fun getTopRatedMovies(page: Int) = repository.getTopRatedMovies(page)
}
