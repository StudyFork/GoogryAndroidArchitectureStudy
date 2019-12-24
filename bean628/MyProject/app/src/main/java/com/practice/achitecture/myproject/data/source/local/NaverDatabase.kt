package com.practice.achitecture.myproject.data.source.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.practice.achitecture.myproject.data.source.local.model.HistoryOfSearch
import com.practice.achitecture.myproject.data.source.local.model.HistoryOfSearchTypeConverter


@Database(version = 1, entities = [HistoryOfSearch::class])
@TypeConverters(HistoryOfSearchTypeConverter::class)
abstract class NaverDatabase : RoomDatabase() {

    abstract fun naverDao(): NaverDao

    companion object {
        private var INSATNCE: NaverDatabase? = null
        private val lock = Any()
        fun getInstance(context: Context): NaverDatabase {
            synchronized(lock) {
                if (INSATNCE == null) {
                    INSATNCE = Room.databaseBuilder(
                        context.applicationContext,
                        NaverDatabase::class.java, "HistoryOfSearch.db"
                    ).build()
                }
            }
            return INSATNCE!!
        }
    }
}