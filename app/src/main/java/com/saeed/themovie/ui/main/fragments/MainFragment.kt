package com.saeed.themovie.ui.main.fragments

import android.view.MenuItem
import android.view.View
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.saeed.themovie.R
import com.saeed.themovie.ui.base.BaseFragment
import com.saeed.themovie.ui.main.adapter.MoviesAdapter
import com.saeed.themovie.ui.main.viewmodels.MainViewModel
import com.saeed.themovie.utils.OnLoadMoreListener
import kotlinx.android.synthetic.main.main_fragment.*
import org.koin.android.viewmodel.ext.android.viewModel

class MainFragment : BaseFragment() {

    private lateinit var adapter: MoviesAdapter
    private val moviesViewModel: MainViewModel by viewModel()
    private val onLoadMoreListener by lazy {
        OnLoadMoreListener(rvNews.layoutManager as LinearLayoutManager) { page ->
            callService(page)
        }
    }

    override fun getViewModel() = moviesViewModel

    override fun onLayout() = R.layout.main_fragment

    override fun initView(view: View) {
        adapter = MoviesAdapter { movieId ->
            goToFragment<MovieDetailFragment>(
                bundle = bundleOf(
                    MovieDetailFragment.KEY_ID to movieId
                )
            )
        }
        rvNews.adapter = adapter
        rvNews.addOnScrollListener(onLoadMoreListener)
        callService(1)
    }

    override fun reloadService() {
        adapter.clear()
        callService(1)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        super.onOptionsItemSelected(item)
        if (item.itemId == R.id.action_filter)
            FilterDialogFragment(childFragmentManager) { start, end ->
                goToFragment<FilteredMoviesFragment>(
                    bundle = bundleOf(
                        FilteredMoviesFragment.KEY_START_DATE to start,
                        FilteredMoviesFragment.KEY_END_DATE to end
                    )
                )
            }
        return true
    }


    private fun callService(page: Int) {
        onLoadMoreListener.loading = true
        moviesViewModel.getTopRatedMovies(page).observe(this, Observer { rsp ->
            rsp.let {
                adapter.appendItem(it.results)
                onLoadMoreListener.loading = false
            }
        })
    }
}