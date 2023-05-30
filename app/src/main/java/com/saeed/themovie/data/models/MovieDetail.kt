package com.saeed.themovie.data.models

import com.google.gson.annotations.SerializedName

data class MovieDetail(
    @SerializedName("popularity") var popularity: Double,
    @SerializedName("vote_count") var voteCount: Int,
    @SerializedName("budget") var budget: Int,
    @SerializedName("poster_path") var posterPath: String,
    @SerializedName("id") var id: Int,
    @SerializedName("adult") var adult: Boolean,
    @SerializedName("genres") var genres: MutableList<Genre>,
    @SerializedName("backdrop_path") var backdropPath: String,
    @SerializedName("homepage") var homepage: String?,
    @SerializedName("imdb_id") var imdbId: String?,
    @SerializedName("original_language") var originalLanguage: String,
    @SerializedName("original_title") var originalTitle: String,
    @SerializedName("overview") var overview: String,
    @SerializedName("production_companies") var productionCompanies: MutableList<ProductionCompanies>,
    @SerializedName("production_countries") var productionCountries: MutableList<ProductionCountries>,
    @SerializedName("release_date") var releaseDate: String,
    @SerializedName("revenue") var revenue: Int,
    @SerializedName("runtime") var runtime: Int,
    @SerializedName("spoken_languages") var spokenLanguages: MutableList<SpokenLanguages>,
    @SerializedName("status") var status: String,
    @SerializedName("tagline") var tagline: String,
    @SerializedName("video") var video: Boolean,
    @SerializedName("title") var title: String,
    @SerializedName("vote_average") var voteAverage: Double
)

data class Genre(
    @SerializedName("id") var id: Int,
    @SerializedName("name") var name: String
)

data class ProductionCompanies(
    @SerializedName("id") var id: Int,
    @SerializedName("logo_path") var logoPath: String,
    @SerializedName("name") var name: String
)

data class ProductionCountries(
    @SerializedName("iso_3166_1") var iso_3166_1: String,
    @SerializedName("name") var name: String
)

data class SpokenLanguages(
    @SerializedName("iso_3166_1") var iso_3166_1: String,
    @SerializedName("name") var name: String
)