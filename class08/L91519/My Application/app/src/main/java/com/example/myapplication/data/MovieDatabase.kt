package com.example.myapplication.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.myapplication.ListTypeConverter
import com.example.myapplication.data.model.Movie

@Database(entities = arrayOf(Movie::class), version = 1, exportSchema = false)

@TypeConverters(ListTypeConverter::class)
abstract class MovieDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao

}
