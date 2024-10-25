package com.example.ibarsanimelist.core.domain.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Anime(
    val id: Int,
    val title: String,
    val titleJapanese: String?,
    val imageUrl: String?,
    val synopsis: String?,
    val score: Double?,
    val episodes: Int?,
    val genres: List<String>?,
    val year: Int?,
    val studios: List<String>?,
    var isFavorite: Boolean
) : Parcelable
