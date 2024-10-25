package com.example.ibarsanimelist.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.ibarsanimelist.core.domain.usecase.AnimeUseCase

class HomeViewModel(animeUseCase: AnimeUseCase) : ViewModel() {

    val anime = animeUseCase.getAllAnime().asLiveData()
}