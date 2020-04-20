package com.mtjin.data.search.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mtjin.data.search.model.Movie

@Database(entities = [Movie::class], version = 1, exportSchema = false)
abstract class MovieDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
}