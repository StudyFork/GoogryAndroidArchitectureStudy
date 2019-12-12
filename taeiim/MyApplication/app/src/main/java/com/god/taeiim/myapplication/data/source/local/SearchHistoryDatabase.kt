package com.god.taeiim.myapplication.data.source.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.god.taeiim.myapplication.data.SearchHistory

@Database(
    entities = [SearchHistory::class], version = 1, exportSchema = false
)
@TypeConverters(ListStringConverter::class)
abstract class SearchHistoryDatabase : RoomDatabase() {

    abstract fun taskDao(): SearchHistoryDao

    companion object {

        private var INSTANCE: SearchHistoryDatabase? = null

        private val lock = Any()

        fun getInstance(context: Context): SearchHistoryDatabase {
            synchronized(lock) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        SearchHistoryDatabase::class.java, "SearchHistory.db"
                    )
                        .allowMainThreadQueries()
                        .build()
                }
                return INSTANCE!!
            }
        }
    }

}
