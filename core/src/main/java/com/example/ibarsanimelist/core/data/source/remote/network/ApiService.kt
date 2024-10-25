package com.example.ibarsanimelist.core.data.source.remote.network

import com.example.ibarsanimelist.core.data.source.remote.response.AnimeResponse
//import com.example.ibarsanimelist.data.source.remote.response.ListAnimeResponse
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("anime")
    suspend fun getList(): AnimeResponse
}