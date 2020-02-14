package com.example.myapplication

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = arrayOf(Movie::class), version = 1, exportSchema = false)

@TypeConverters(ListTypeConverter::class)
abstract class MovieDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao

    companion object {

        private var instance: MovieDatabase? = null
        private val lock = Any()

        fun getInstance(context: Context): MovieDatabase {

            synchronized(lock)
            {
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        MovieDatabase::class.java,
                        "Movies.db"
                    ).allowMainThreadQueries().build()
                }
                return instance!!
            }
        }
    }
}
