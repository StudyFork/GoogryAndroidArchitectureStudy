package com.example.androidarchitecture.data.datasource.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

import com.example.androidarchitecture.data.datasource.database.dao.BlogDao
import com.example.androidarchitecture.data.datasource.database.dao.ImageDao
import com.example.androidarchitecture.data.datasource.database.dao.KinDao
import com.example.androidarchitecture.data.datasource.database.dao.MovieDao
import com.example.androidarchitecture.data.response.BlogData
import com.example.androidarchitecture.data.response.ImageData
import com.example.androidarchitecture.data.response.KinData
import com.example.androidarchitecture.data.response.MovieData


@Database(
    entities = [BlogData::class, MovieData::class, ImageData::class, KinData::class],
    version = 1, exportSchema = false
)
abstract class SearchHistDatabase : RoomDatabase() {

    abstract fun blogDao(): BlogDao
    abstract fun movieDao(): MovieDao
    abstract fun imageDao(): ImageDao
    abstract fun kinDao(): KinDao


    companion object {
        @Volatile
        private var isntance: SearchHistDatabase? = null

        fun getInstance(context: Context): SearchHistDatabase =
            isntance ?: synchronized(this) {
                Room.databaseBuilder(
                    context.applicationContext,
                    SearchHistDatabase::class.java,
                    "search_history.db"
                ).build().also {
                    isntance = it
                }


            }


    }
}