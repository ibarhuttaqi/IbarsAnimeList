package com.example.ibarsanimelist.core.utils

import com.example.ibarsanimelist.core.data.source.local.entity.AnimeEntity
import com.example.ibarsanimelist.core.data.source.remote.response.DataItem
import com.example.ibarsanimelist.core.domain.model.Anime
import com.example.ibarsanimelist.core.ui.AnimeUIModel

object DataMapper {

    fun mapResponseToDomain(input: List<DataItem?>): List<Anime> {
        return input.map { dataItem -> // kalau pake flatmap maka data return tidak jadi list, kalau pake map maka akan jadi list
            Anime(
                id = dataItem?.malId ?: 0,
                title = dataItem?.title ?: "",
                titleJapanese = dataItem?.titleJapanese ?: "",
                imageUrl = dataItem?.images?.jpg?.imageUrl ?: "",
                synopsis = dataItem?.synopsis ?: "",
                score = dataItem?.score ?: 0.0,
                episodes = dataItem?.episodes ?: 0,
                genres = dataItem?.genres?.map { it?.name ?: "" } ?: emptyList(),
                year = dataItem?.year ?: 0,
                studios = dataItem?.studios?.map { it?.name ?: "" } ?: emptyList(),
                isFavorite = false  // default isFavorite
            )
        }
    }

    fun mapEntityToDomain(input: AnimeEntity): Anime = Anime(
        id = input.id,
        title = input.title,
        titleJapanese = input.titleJapanese,
        imageUrl = input.imageUrl,
        synopsis = input.synopsis,
        score = input.score,
        episodes = input.episodes,
        genres = input.genres,
        year = input.year,
        studios = input.studios,
        isFavorite = input.isFavorite
    )

    // Mapping dari entity lokal ke domain
    fun mapEntitiesToDomain(input: List<AnimeEntity>): List<Anime> {
        return input.map { entity ->
            Anime(
                id = entity.id,
                title = entity.title,
                titleJapanese = entity.titleJapanese,
                imageUrl = entity.imageUrl,
                synopsis = entity.synopsis,
                score = entity.score,
                episodes = entity.episodes,
                genres = entity.genres,
                year = entity.year,
                studios = entity.studios,
                isFavorite = entity.isFavorite
            )
        }
    }

    // Mapping dari domain ke entity lokal untuk list Anime
    fun mapDomainToEntity(inputList: List<Anime>): List<AnimeEntity> {
        return inputList.map { mapDomainToEntity(it) }
    }

    // Mapping dari domain ke entity lokal
    fun mapDomainToEntity(input: Anime): AnimeEntity {
        return AnimeEntity(
            id = input.id,
            title = input.title,
            titleJapanese = input.titleJapanese,
            imageUrl = input.imageUrl,
            synopsis = input.synopsis,
            score = input.score,
            episodes = input.episodes,
            genres = input.genres,
            year = input.year,
            studios = input.studios,
            isFavorite = input.isFavorite
        )
    }

    // Map Domain Model to UI Model
    fun mapDomainToUI(input: Anime): AnimeUIModel {
        return AnimeUIModel(
            id = input.id,
            title = input.title,
            titleJapanese = input.titleJapanese,  // UI model may contain more details
            imageUrl = input.imageUrl,
            synopsis = input.synopsis,
            score = input.score,
            episodes = input.episodes,
            genres = input.genres,
            year = input.year,
            studios = input.studios,
            isFavorite = input.isFavorite
        )
    }

    // Function to map Anime (domain model) to AnimeUIModel (UI model)
    fun mapUIToDomain(anime: AnimeUIModel): Anime {
        return Anime(
            id = anime.id,
            title = anime.title,
            titleJapanese = anime.titleJapanese,  // UI model may contain more details
            imageUrl = anime.imageUrl,
            synopsis = anime.synopsis,
            score = anime.score,
            episodes = anime.episodes,
            genres = anime.genres,
            year = anime.year,
            studios = anime.studios,
            isFavorite = anime.isFavorite
        )
    }

}
