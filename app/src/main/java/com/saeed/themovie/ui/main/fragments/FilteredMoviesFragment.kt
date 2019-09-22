package com.saeed.themovie.ui.main.fragments

import android.content.Context
import android.view.MenuItem
import android.view.View
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.saeed.themovie.R
import com.saeed.themovie.ui.base.BaseFragment
import com.saeed.themovie.ui.main.adapter.MoviesAdapter
import com.saeed.themovie.ui.main.viewmodels.FilteredMoviesViewModel
import com.saeed.themovie.utils.OnLoadMoreListener
import kotlinx.android.synthetic.main.main_fragment.*
import org.koin.android.viewmodel.ext.android.viewModel

class FilteredMoviesFragment : BaseFragment() {

    companion object {
        const val KEY_START_DATE = "START_DATE"
        const val KEY_END_DATE = "END_DATE"
    }

    private lateinit var startDate: String
    private lateinit var endDate: String
    private lateinit var adapter: MoviesAdapter
    private val moviesViewModel: FilteredMoviesViewModel by viewModel()
    private val onLoadMoreListener by lazy {
        OnLoadMoreListener(rvNews.layoutManager as LinearLayoutManager) { page ->
            callService(page, startDate, endDate)
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        arguments?.let {
            it.getString(KEY_START_DATE)?.let { d ->
                startDate = d
            }
            it.getString(KEY_END_DATE)?.let { d ->
                endDate = d
            }
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
        callService(1, startDate, endDate)
    }

    override fun reloadService() {
        adapter.clear()
        callService(1, startDate, endDate)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        super.onOptionsItemSelected(item)
        if (item.itemId == R.id.action_filter)
            FilterDialogFragment(childFragmentManager) { start, end ->
                refreshingList(start, end)
            }
        return true
    }

    private fun refreshingList(start: String, end: String) {
        adapter.clear()
        callService(1, start, end)
    }

    private fun callService(page: Int, start: String, end: String) {
        onLoadMoreListener.loading = true
        moviesViewModel.getDiscoveredMovies(page, start, end).observe(this, Observer { rsp ->
            rsp.let {
                adapter.appendItem(it.results)
                onLoadMoreListener.loading = false
            }
        })
    }
}