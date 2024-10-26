package com.example.ibarsanimelist.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.ibarsanimelist.core.domain.usecase.AnimeUseCase

class FavoriteViewModel(animeUseCase: AnimeUseCase): ViewModel() {
    val favoriteAnime = animeUseCase.getFavoriteAnime().asLiveData()
}