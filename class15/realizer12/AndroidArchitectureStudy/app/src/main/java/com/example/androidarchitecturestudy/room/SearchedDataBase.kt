package com.example.androidarchitecturestudy.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
@Database(
    entities = [MovieSearchEntity::class],
    version = 1,
    exportSchema = false
)
abstract class SearchedDataBase : RoomDatabase() {
    abstract fun getMovieSearchedDao(): MovieSearchedDao
    companion object {
        private var INSTANCE: SearchedDataBase? = null
        fun getInstance(context: Context): SearchedDataBase? {
            if (INSTANCE == null) {
                synchronized(SearchedDataBase::class) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        SearchedDataBase::class.java, "searchedQuery.db"
                    ).allowMainThreadQueries()
                      .build()
                }
            }
            return INSTANCE
        }
    }
}