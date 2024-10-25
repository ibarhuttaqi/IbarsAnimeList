package com.example.ibarsanimelist.core.data.source.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.ibarsanimelist.core.data.source.local.Converters
import com.example.ibarsanimelist.core.data.source.local.entity.AnimeEntity

@Database(entities = [AnimeEntity::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class AnimeDatabase : RoomDatabase() {
    abstract fun animeDao(): AnimeDao

    companion object {
        @Volatile
        private var INSTANCE: AnimeDatabase? = null

        fun getInstance(context: Context): AnimeDatabase =
            INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AnimeDatabase::class.java,
                    "Anime.db"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
    }
}