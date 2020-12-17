package com.jay.aas.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.jay.aas.model.Movie
import com.jay.aas.model.SearchHistory

@Database(
    entities = [Movie::class, SearchHistory::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun movieDao(): MovieDao
    abstract fun searchHistoryDao(): SearchHistoryDao

}