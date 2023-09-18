package com.raisproject.core.data.source.remote.network

import com.raisproject.core.BuildConfig
import com.raisproject.core.data.source.remote.response.MoviesResponse
import retrofit2.http.GET

interface ApiService {
    @GET("movie/now_playing?api_key=${BuildConfig.API_KEY}")
    suspend fun getList(): MoviesResponse
}