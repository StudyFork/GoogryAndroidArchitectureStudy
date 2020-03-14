package com.example.myapplication.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.myapplication.model.MovieEntity

@Database(entities = [MovieEntity::class], version = 1, exportSchema = false)
abstract class MovieDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao

    companion object {

        private var Instance: MovieDatabase? = null
        private const val DATABASE_NAME = "movie-database"

        @Synchronized
        fun getDatabase(context: Context): MovieDatabase {
            synchronized(this) {
                if (Instance == null) {
                    Instance = Room.databaseBuilder(
                        context.applicationContext,
                        MovieDatabase::class.java, DATABASE_NAME
                    )
                        .allowMainThreadQueries()
                        .build()
                }
                return Instance!!
            }
        }
    }
}


