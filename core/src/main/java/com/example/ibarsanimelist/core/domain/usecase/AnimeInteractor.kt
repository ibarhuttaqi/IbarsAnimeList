package com.example.ibarsanimelist.core.domain.usecase

import com.example.ibarsanimelist.core.domain.model.Anime
import com.example.ibarsanimelist.core.domain.repository.IAnimeRepository
import kotlinx.coroutines.flow.Flow

class AnimeInteractor(private val animeRepository: IAnimeRepository): AnimeUseCase {

    override fun getAllAnime() = animeRepository.getAllAnime()

    override fun getFavoriteAnime() = animeRepository.getFavoriteAnime()

    override suspend fun setFavoriteAnime(anime: Anime, state: Boolean) = animeRepository.setFavoriteAnime(anime, state)

    override fun getAnimeById(id: Int): Flow<Anime?> = animeRepository.getAnimeById(id)
}