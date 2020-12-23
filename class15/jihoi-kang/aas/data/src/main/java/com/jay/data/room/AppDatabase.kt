package com.jay.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.jay.data.model.Movie
import com.jay.data.model.SearchHistory

@Database(
    entities = [Movie::class, SearchHistory::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun movieDao(): MovieDao
    abstract fun searchHistoryDao(): SearchHistoryDao

}