package com.example.ibarsanimelist.di

import com.example.ibarsanimelist.core.domain.usecase.AnimeInteractor
import com.example.ibarsanimelist.core.domain.usecase.AnimeUseCase
import com.example.ibarsanimelist.ui.detail.DetailAnimeViewModel
import com.example.ibarsanimelist.ui.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<AnimeUseCase> { AnimeInteractor(get()) }
}

val viewModelModule = module {
    viewModel { HomeViewModel(get()) }
    viewModel { DetailAnimeViewModel(get()) }
}