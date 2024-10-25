package com.example.ibarsanimelist.core.data.source.local

import android.util.Log
import com.example.ibarsanimelist.core.data.source.local.entity.AnimeEntity
import com.example.ibarsanimelist.core.data.source.local.room.AnimeDao
import kotlinx.coroutines.flow.Flow

class LocalDataSource(private val animeDao: AnimeDao) {

    companion object {
        private var instance: LocalDataSource? = null

        fun getInstance(animeDao: AnimeDao): LocalDataSource =
            instance ?: synchronized(this) {
                instance ?: LocalDataSource(animeDao)
            }
    }

    fun getAllAnime(): Flow<List<AnimeEntity>> = animeDao.getAllAnime()

    fun getFavoriteAnime(): Flow<List<AnimeEntity>> = animeDao.getFavoriteAnime()

    suspend fun insertAnime(animeList: List<AnimeEntity>) = animeDao.insertAnime(animeList)

    suspend fun setFavoriteAnime(anime: AnimeEntity, newState: Boolean) {
        Log.d("LocalDataSource", "Setting favorite anime newState: $newState")
        Log.d("LocalDataSource", "Setting favorite anime anime.isFavorite: ${anime.isFavorite}")
        anime.isFavorite = newState
        animeDao.updateFavoriteAnime(anime)
    }

    fun getAnimeById(id: Int): Flow<AnimeEntity?> = animeDao.getAnimeById(id)
}