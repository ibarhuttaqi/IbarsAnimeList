package com.example.ibarsanimelist.core.domain.usecase

import com.example.ibarsanimelist.core.data.Resource
import com.example.ibarsanimelist.core.domain.model.Anime
import kotlinx.coroutines.flow.Flow

interface AnimeUseCase {
    fun getAllAnime(): Flow<Resource<List<Anime>>>

    fun getFavoriteAnime(): Flow<List<Anime>>

    suspend fun setFavoriteAnime(aAnime: Anime, state: Boolean)

    fun getAnimeById(id: Int): Flow<Anime?>
}