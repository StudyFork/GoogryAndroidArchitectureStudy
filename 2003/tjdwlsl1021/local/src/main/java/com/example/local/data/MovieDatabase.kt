package com.example.local.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.local.MovieItem

@Database(entities = [MovieItem::class], version = 1, exportSchema = false)
abstract class MovieDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
}


