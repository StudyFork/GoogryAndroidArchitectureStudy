package com.hong.data.source.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.hong.data.source.local.dao.MovieDao
import com.hong.data.source.local.entity.MovieInfo

@Database(entities = [MovieInfo::class], version = 1)
abstract class MovieDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao

    companion object {
        @Volatile
        private var INSTANCE: MovieDatabase? = null

        fun getInstance(context: Context): MovieDatabase? {
            if (INSTANCE == null) {
                synchronized(MovieDatabase::class) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        MovieDatabase::class.java, "movie.db"
                    ).fallbackToDestructiveMigration().build()
                }
            }
            return INSTANCE
        }

    }
}