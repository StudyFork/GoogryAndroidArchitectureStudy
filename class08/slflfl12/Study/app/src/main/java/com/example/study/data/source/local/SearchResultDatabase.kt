package com.example.study.data.source.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.study.data.source.local.model.SearchResult

@Database(version = 1, entities = [SearchResult::class], exportSchema = false)
abstract class SearchResultDatabase : RoomDatabase() {

    abstract fun searchResultDao(): SearchResultDao



}