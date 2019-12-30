package com.example.androidstudy.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.androidstudy.model.data.SearchResultEntity

@Database(entities = [(SearchResultEntity::class)], version = 1)
abstract class SearchResultDatabase : RoomDatabase() {

    abstract fun SearchResultDao(): SearchResultDao

    companion object {
        private var INSTANCE: SearchResultDatabase? = null

        fun getInstance(context: Context): SearchResultDatabase? {
            if (INSTANCE == null) {
                synchronized(SearchResultDatabase::class) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        SearchResultDatabase::class.java, "searchData.db"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                }
            }
            return INSTANCE
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }
}