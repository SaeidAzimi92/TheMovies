package com.saeed.themovie.ui.main.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.saeed.themovie.data.models.ServiceStatus
import com.saeed.themovie.data.repository.BaseRepository

open class BaseViewModel(repository: BaseRepository) : ViewModel() {
    var onServiceStatus: MutableLiveData<ServiceStatus> = repository.onLoading
}