package com.saeed.themovie.data.restprovider

import com.saeed.themovie.data.models.MovieDetail
import com.saeed.themovie.data.models.MoviesList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RestApis {

    @GET("movie/{movie_id}")
    fun getMovieDetail(
        @Path("movie_id") id: Int,
        @Query("api_key") apiKey: String
    ): Call<MovieDetail>

    @GET("movie/top_rated")
    fun getTopRatedMovies(
        @Query("api_key") apiKey: String,
        @Query("page") page: Int
    ): Call<MoviesList>

    @GET("discover/movie")
    fun discoveredMovies(
        @Query("api_key") apiKey: String,
        @Query("page") page: Int,
        @Query("release_date.gte") startDate: String,
        @Query("release_date.lte") endDate: String
    ): Call<MoviesList>
}