//package com.example.ibarsanimelist.core.di
//
//import android.content.Context
//import com.example.ibarsanimelist.core.data.AnimeRepository
//import com.example.ibarsanimelist.core.data.source.local.LocalDataSource
//import com.example.ibarsanimelist.core.data.source.local.room.AnimeDatabase
//import com.example.ibarsanimelist.core.data.source.remote.RemoteDataSource
//import com.example.ibarsanimelist.core.data.source.remote.network.ApiConfig
//import com.example.ibarsanimelist.core.domain.repository.IAnimeRepository
//import com.example.ibarsanimelist.core.domain.usecase.AnimeInteractor
//import com.example.ibarsanimelist.core.domain.usecase.AnimeUseCase
//
//object Injection {
//    private fun provideRepository(context: Context): IAnimeRepository {
//        val database = AnimeDatabase.getInstance(context)
//
//        val remoteDataSource = RemoteDataSource.getInstance(ApiConfig.provideApiService())
//        val localDataSource = LocalDataSource.getInstance(database.animeDao())
////        val appExecutors = AppExecutors()
//
//        return AnimeRepository.getInstance(remoteDataSource, localDataSource)
//    }
//
//    fun provideAnimeUseCase(context: Context): AnimeUseCase {
//        val repository = provideRepository(context)
//        return AnimeInteractor(repository)
//    }
//}