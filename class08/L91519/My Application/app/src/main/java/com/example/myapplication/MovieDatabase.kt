package com.example.myapplication

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(Movie::class), version = 1)

abstract class MovieDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao

    companion object {

        private var instance: MovieDatabase? = null

//        fun getInstance(context: Context): MovieDatabase? {
//
//            return instance ?: synchronized(MovieDatabase::class) {
//                instance ?: Room.databaseBuilder(
//                    context.applicationContext,
//                    MovieDatabase::class.java,
//                    "Movies.db"
//                ).allowMainThreadQueries().build().apply { instance = this }
//            }
//
//
//        }
    }
}
