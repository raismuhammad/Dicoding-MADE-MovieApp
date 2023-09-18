package com.raisproject.core.data.source.remote.response

import com.google.gson.annotations.SerializedName


data class MoviesResponse(

    @field:SerializedName("results")
    val results: List<ResultMovies>
)

data class ResultMovies(

    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("title")
    val title: String? = null,

    @field:SerializedName("original_title")
    val original_title: String? = null,

    @field:SerializedName("poster_path")
    val poster_path: String? = null,

    @field:SerializedName("backdrop_path")
    val backdrop_path: String? = null,

    @field:SerializedName("release_date")
    val release_date: String? = null,

    @field:SerializedName("overview")
    val overview: String? = null,

    @field:SerializedName("popularity")
    val popularity: Double? = null,

    @field:SerializedName("vote_average")
    val vote_average: Double? = null,
)
