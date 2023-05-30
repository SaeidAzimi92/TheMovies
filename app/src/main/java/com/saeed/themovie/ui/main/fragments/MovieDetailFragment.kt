package com.saeed.themovie.ui.main.fragments

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.saeed.themovie.R
import com.saeed.themovie.data.models.Genre
import com.saeed.themovie.data.models.MovieDetail
import com.saeed.themovie.ui.base.BaseFragment
import com.saeed.themovie.ui.main.viewmodels.MovieDetailViewModel
import com.saeed.themovie.utils.ImageLoader
import kotlinx.android.synthetic.main.fragment_movie_detail.*
import org.koin.android.viewmodel.ext.android.viewModel

class MovieDetailFragment : BaseFragment() {

    companion object {
        const val KEY_ID = "ID"
    }

    private var movieId = 0
    private val moviesViewModel: MovieDetailViewModel by viewModel()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        arguments?.getInt(KEY_ID)?.let {
            movieId = it
        }
    }

    override fun onLayout() = R.layout.fragment_movie_detail

    override fun initView(view: View) {
    }

    override fun getViewModel() = moviesViewModel

    override fun reloadService() {
        callService()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        callService()
    }

    private fun callService() {
        moviesViewModel.getMovieDetail(movieId).observe(this, Observer { rsp ->
            updateUI(rsp)
        })
    }

    private fun updateUI(data: MovieDetail) {
        tvTitle.text = data.title
        tvDescription.text = data.overview
        ImageLoader.loadW500(data.backdropPath, imgMovie)
        setGenres(data.genres)
    }

    private fun setGenres(data: MutableList<Genre>) {
        val genres = StringBuilder()
        genres.append("Genre: ")
        data.forEach {
            genres.append("${it.name}, ")
        }
        genres.deleteCharAt(genres.lastIndex - 1)
        tvGenres.text = genres
    }
}