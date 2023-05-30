package com.saeed.themovie.di

import com.saeed.themovie.data.repository.DiscoveredMoviesRepository
import com.saeed.themovie.data.repository.MovieDetailRepository
import com.saeed.themovie.data.repository.MoviesRepository
import com.saeed.themovie.ui.main.viewmodels.FilteredMoviesViewModel
import com.saeed.themovie.ui.main.viewmodels.MainViewModel
import com.saeed.themovie.ui.main.viewmodels.MovieDetailViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModels = module {

    viewModel { MainViewModel(get()) }
    viewModel { MovieDetailViewModel(get()) }
    viewModel { FilteredMoviesViewModel(get()) }
}
val repositoryModels = module {

    single { MoviesRepository() }
    single { MovieDetailRepository() }
    single { DiscoveredMoviesRepository() }
}