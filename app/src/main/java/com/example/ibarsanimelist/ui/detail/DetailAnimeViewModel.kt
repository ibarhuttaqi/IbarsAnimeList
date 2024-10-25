package com.example.ibarsanimelist.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ibarsanimelist.core.domain.model.Anime
import com.example.ibarsanimelist.core.domain.usecase.AnimeUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailAnimeViewModel(private val animeUseCase: AnimeUseCase) : ViewModel() {
    fun setFavoriteAnime(anime: Anime, newStatus:Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            animeUseCase.setFavoriteAnime(anime, newStatus)
        }
    }
    fun getAnimeById(id: Int) = animeUseCase.getAnimeById(id)
}