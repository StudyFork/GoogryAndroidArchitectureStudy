package com.jay.aas.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.jay.aas.model.Movie
import com.jay.aas.model.SearchHistory

@Database(
    entities = [Movie::class, SearchHistory::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun movieDao(): MovieDao
    abstract fun searchHistoryDao(): SearchHistoryDao

    companion object {

        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            if (INSTANCE == null) {
                synchronized(AppDatabase::class) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        "Movie.db"
                    )
                        .allowMainThreadQueries()
                        .build()
                }
            }

            return INSTANCE!!
        }

        fun destroyDataBase() {
            INSTANCE = null
        }

    }
}