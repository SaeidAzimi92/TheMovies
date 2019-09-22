package com.saeed.themovie.data.restprovider

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL = "https://api.themoviedb.org/3/"

object ServiceGenerator {

    operator fun invoke(): RestApis {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(RestApis::class.java)
    }
}