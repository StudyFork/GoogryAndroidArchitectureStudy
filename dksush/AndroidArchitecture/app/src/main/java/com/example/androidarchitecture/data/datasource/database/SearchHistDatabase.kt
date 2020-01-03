package com.example.androidarchitecture.data.datasource.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

import com.example.androidarchitecture.data.datasource.database.dao.BlogDao
import com.example.androidarchitecture.data.response.BlogData


@Database(
    entities = [BlogData::class],
    version = 1, exportSchema = false
)
abstract class SearchHistDatabase : RoomDatabase() {

    abstract fun blofDao(): BlogDao

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