package com.example.study.data.source.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.study.data.source.local.model.SearchResult

@Database(version = 1, entities = [SearchResult::class])
abstract class SearchResultDatabase : RoomDatabase() {

    abstract fun searchResultDao(): SearchResultDao


    companion object {

        private var instance: SearchResultDatabase? = null

        fun getInstance(context: Context): SearchResultDatabase? {

            instance ?: synchronized(SearchResultDatabase::class) {
                instance ?: Room.databaseBuilder(context.applicationContext, SearchResultDatabase::class.java, "SearchResult.db").allowMainThreadQueries().build().apply { instance = this}
            }

            return instance!!
        }
    }
}