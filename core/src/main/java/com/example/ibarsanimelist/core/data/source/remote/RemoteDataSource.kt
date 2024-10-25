package com.example.ibarsanimelist.core.data.source.remote

import android.util.Log
import com.example.ibarsanimelist.core.data.source.remote.network.ApiResponse
import com.example.ibarsanimelist.core.data.source.remote.network.ApiService
import com.example.ibarsanimelist.core.data.source.remote.response.AnimeResponse
import com.example.ibarsanimelist.core.data.source.remote.response.DataItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RemoteDataSource(private val apiService: ApiService) {
    companion object {
        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(service: ApiService): RemoteDataSource =
            instance ?: synchronized(this) {
                instance ?: RemoteDataSource(service)
            }
    }

    suspend fun getAllAnime(): Flow<ApiResponse<List<DataItem?>>> {
        return flow {
            try {
                val response = apiService.getList()
                val dataArray = response.animeList
                Log.d("RemoteDataSource", "raw: $response")
                Log.d("RemoteDataSource", "dataArray: $dataArray")
                if (dataArray?.isNotEmpty() == true){
                    emit(ApiResponse.Success(response.animeList))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e : Exception){
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)

    }
}