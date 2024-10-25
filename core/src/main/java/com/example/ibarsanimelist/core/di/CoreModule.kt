package com.example.ibarsanimelist.core.di

import androidx.room.Room
import com.example.ibarsanimelist.core.BuildConfig
import com.example.ibarsanimelist.core.data.AnimeRepository
import com.example.ibarsanimelist.core.data.source.local.LocalDataSource
import com.example.ibarsanimelist.core.data.source.local.room.AnimeDatabase
import com.example.ibarsanimelist.core.data.source.remote.RemoteDataSource
import com.example.ibarsanimelist.core.data.source.remote.network.ApiService
import com.example.ibarsanimelist.core.domain.repository.IAnimeRepository
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

private val baseUrl = BuildConfig.BASE_URL
private val loggingInterceptor =
    if(BuildConfig.DEBUG) {
        HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    } else {
        HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.NONE)
    }

val databaseModule = module {
    factory { get<AnimeDatabase>().animeDao() }
    single {
        Room.databaseBuilder(
            androidContext(),
            AnimeDatabase::class.java, "Anime.db"
        ).fallbackToDestructiveMigration().build()
    }
}

val networkModule = module {
    single {
        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .build()
    }
    single {
        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
        retrofit.create(ApiService::class.java)
    }
}

val repositoryModule = module {
    single { LocalDataSource(get()) }
    single { RemoteDataSource(get()) }
    single<IAnimeRepository> { AnimeRepository(get(), get()) }
}