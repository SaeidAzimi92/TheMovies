package com.saeed.themovie.utils

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

open class OnLoadMoreListener(
    private val mLinearLayoutManager: LinearLayoutManager,
    var onLoadMore: (page: Int) -> Unit
) :
    RecyclerView.OnScrollListener() {
    var loading = false
    private var currentPage = 1

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)
        if (dy > 0) {
            val visibleItemCount = recyclerView.childCount
            val totalItemCount = mLinearLayoutManager.itemCount
            val firstVisibleItem = mLinearLayoutManager.findFirstVisibleItemPosition()

            if (loading)
                return

            if (totalItemCount - visibleItemCount <= firstVisibleItem) {
                currentPage++
                onLoadMore(currentPage)
                loading = true
            }
        }
    }
}