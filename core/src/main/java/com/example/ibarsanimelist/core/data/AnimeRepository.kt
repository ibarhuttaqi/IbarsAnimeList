package com.example.ibarsanimelist.core.data

import android.util.Log
import com.example.ibarsanimelist.core.data.source.local.LocalDataSource
import com.example.ibarsanimelist.core.data.source.remote.RemoteDataSource
import com.example.ibarsanimelist.core.data.source.remote.network.ApiResponse
import com.example.ibarsanimelist.core.domain.model.Anime
import com.example.ibarsanimelist.core.domain.repository.IAnimeRepository
import com.example.ibarsanimelist.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

class AnimeRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
) : IAnimeRepository {

    companion object {
        @Volatile
        private var instance: AnimeRepository? = null

        fun getInstance(
            remoteData: RemoteDataSource,
            localData: LocalDataSource,
        ): AnimeRepository =
            instance ?: synchronized(this) {
                instance
                    ?: AnimeRepository(remoteData, localData)
            }
    }

    override fun getAllAnime(): Flow<Resource<List<Anime>>> = flow {
        emit(Resource.Loading())
        try {
            remoteDataSource.getAllAnime().collect { apiResponse ->
                when (apiResponse) {
                    is ApiResponse.Success -> {
                        val animeList = DataMapper.mapResponseToDomain(apiResponse.data)
                        emit(Resource.Success(animeList))

                        // Setelah data berhasil diambil, lakukan penyimpanan ke database
                        try {
                            val animeEntities = DataMapper.mapDomainToEntity(animeList)
                            localDataSource.insertAnime(animeEntities) // Operasi insert ke database
                        } catch (e: Exception) {
                            emit(Resource.Error("Failed to insert data to database: ${e.message}"))
                        }

                        Log.d("AnimeRepository", "apiResponse: $apiResponse")
                        Log.d("AnimeRepository", "getAllAnime: $animeList")
                    }
                    is ApiResponse.Empty -> {
                        emit(Resource.Success(emptyList()))
                    }
                    is ApiResponse.Error -> {
                        emit(Resource.Error(apiResponse.errorMessage))
                    }
                }
            }
        } catch (e: Exception) {
            emit(Resource.Error(e.message.toString()))
        }
    }

    override fun getFavoriteAnime(): Flow<List<Anime>> {
        return localDataSource.getFavoriteAnime().map { animeEntities ->
            DataMapper.mapEntitiesToDomain(animeEntities)
        }
    }

    override suspend fun setFavoriteAnime(anime: Anime, state: Boolean) {
        val animeEntity = DataMapper.mapDomainToEntity(anime).apply {
            this.isFavorite = state // Set status favorit dengan benar
        }
        localDataSource.setFavoriteAnime(animeEntity, state)
    }

    override fun getAnimeById(id: Int): Flow<Anime?> {
        return localDataSource.getAnimeById(id).map { animeEntity ->
            animeEntity?.let { DataMapper.mapEntityToDomain(it) }  // Memetakan dari Entity ke Domain
        }
    }
}