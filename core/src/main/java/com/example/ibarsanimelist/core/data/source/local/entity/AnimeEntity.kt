package com.example.ibarsanimelist.core.data.source.local.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "anime")
data class AnimeEntity(
    @PrimaryKey
    @ColumnInfo(name = "animeId")
    val id: Int,

    @ColumnInfo(name = "title")
    val title: String,

    @ColumnInfo(name = "titleJapanese")
    val titleJapanese: String?,

    @ColumnInfo(name = "imageUrl")
    val imageUrl: String?,

    @ColumnInfo(name = "synopsis")
    val synopsis: String?,

    @ColumnInfo(name = "score")
    val score: Double?,

    @ColumnInfo(name = "episodes")
    val episodes: Int?,

    @ColumnInfo(name = "genres")
    val genres: List<String>?,

    @ColumnInfo(name = "year")
    val year: Int?,

    @ColumnInfo(name = "studios")
    val studios: List<String>?,

    @ColumnInfo(name = "isFavorite")
    var isFavorite: Boolean = false
) : Parcelable
