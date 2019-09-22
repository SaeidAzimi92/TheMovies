package com.saeed.themovie.data.models

import com.google.gson.annotations.SerializedName

data class MoviesList(
    @SerializedName("page") var page: Int,
    @SerializedName("total_results") var totalResults: Int,
    @SerializedName("total_pages") var totalPages: Int,
    @SerializedName("results") var results: MutableList<Movie>
)

data class Movie(
    @SerializedName("popularity") var popularity: Double,
    @SerializedName("vote_count") var voteCount: Int,
    @SerializedName("video") var video: Boolean,
    @SerializedName("poster_path") var posterPath: String,
    @SerializedName("id") var id: Int,
    @SerializedName("adult") var adult: Boolean,
    @SerializedName("backdrop_path") var backdropPath: String,
    @SerializedName("original_language") var originalLanguage: String,
    @SerializedName("original_title") var originalTitle: String,
    @SerializedName("genre_ids") var genreIds: MutableList<Int>,
    @SerializedName("title") var title: String,
    @SerializedName("vote_average") var voteAverage: Double,
    @SerializedName("overview") var overview: String,
    @SerializedName("release_date") var releaseDate: String
)