package com.saeed.themovie.data.repository

import androidx.lifecycle.MutableLiveData
import com.saeed.themovie.data.models.ServiceStatus
import com.saeed.themovie.data.restprovider.ServiceGenerator
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

open class BaseRepository {
    val onLoading = MutableLiveData<ServiceStatus>()

    companion object {
        const val API_KEY = "1ea13a42a32e4dcaca2b1fbebee57597"
        val services = ServiceGenerator.invoke()
    }

    fun <T, R> callService(callBack: Call<T>): MutableLiveData<R> {
        onLoading.value = ServiceStatus.ONLOADING
        val data = MutableLiveData<R>()
        callBack.enqueue(object : Callback<T> {
            override fun onFailure(call: Call<T>, t: Throwable) {
                onLoading.value = ServiceStatus.FAIELD
            }

            override fun onResponse(
                call: Call<T>,
                response: Response<T>
            ) {
                if (response.isSuccessful) {
                    data.value = response.body() as R
                    onLoading.value = ServiceStatus.SUCCESS
                } else
                    onLoading.value = ServiceStatus.FAIELD
            }
        })
        return data
    }

}