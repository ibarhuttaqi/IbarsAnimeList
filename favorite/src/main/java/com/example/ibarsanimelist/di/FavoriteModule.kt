package com.example.ibarsanimelist.di

import com.example.ibarsanimelist.favorite.FavoriteViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val FavoriteModule = module {
    viewModel { FavoriteViewModel(get()) }
}