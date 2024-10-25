package com.example.ibarsanimelist.core.data.source.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.ibarsanimelist.core.data.source.local.entity.AnimeEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface AnimeDao {
    @Query("SELECT * FROM anime")
    fun getAllAnime(): Flow<List<AnimeEntity>>

    @Query("SELECT * FROM anime where isFavorite = 1")
    fun getFavoriteAnime(): Flow<List<AnimeEntity>>

//    @Insert(onConflict = OnConflictStrategy.REPLACE) // akan replace jika ada data yg sama
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAnime(anime: List<AnimeEntity>)

    @Update
    suspend fun updateFavoriteAnime(anime: AnimeEntity)

    @Query("SELECT * FROM anime WHERE animeId = :id")
    fun getAnimeById(id: Int): Flow<AnimeEntity?>
}