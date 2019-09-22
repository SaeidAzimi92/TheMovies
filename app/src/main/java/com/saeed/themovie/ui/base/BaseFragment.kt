package com.saeed.themovie.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.saeed.themovie.data.models.ServiceStatus
import com.saeed.themovie.ui.main.MainActivity
import com.saeed.themovie.ui.main.viewmodels.BaseViewModel
import com.saeed.themovie.utils.extensions.hide
import com.saeed.themovie.utils.extensions.show
import kotlinx.android.synthetic.main.loading.*

abstract class BaseFragment : Fragment() {
    var onViewModel: BaseViewModel? = null
    abstract fun onLayout(): Int
    abstract fun initView(view: View)
    abstract fun getViewModel(): BaseViewModel
    abstract fun reloadService()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(onLayout(), container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView(view)
        getViewModel().onServiceStatus.observe(this, Observer { status ->
            loading?.let { progress ->
                when (status) {
                    ServiceStatus.ONLOADING -> onLoadingAction(progress)
                    ServiceStatus.SUCCESS -> onSuccessAction(progress)
                    ServiceStatus.FAIELD -> onErrorAction(progress)
                }
            }
        })
    }

    private fun onLoadingAction(progress: ProgressBar) {
        progress.show()
        errMsg.visibility = View.GONE
        btnRetry.visibility = View.GONE
    }

    private fun onErrorAction(progress: ProgressBar) {
        progress.hide()
        btnRetry.setOnClickListener {
            reloadService()
        }
        btnRetry.visibility = View.VISIBLE
        errMsg.visibility = View.VISIBLE
    }

    private fun onSuccessAction(progress: ProgressBar) {
        progress.hide()
        errMsg.visibility = View.GONE
        btnRetry.visibility = View.GONE
    }

    inline fun <reified F : BaseFragment> goToFragment(
        bundle: Bundle? = null
    ) {
        (activity as MainActivity).goToFragment<F>(hasAnimation = true, bundle = bundle)
    }
}