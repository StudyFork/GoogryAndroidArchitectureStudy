package com.camai.archtecherstudy.data.source.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [RecentSearchName::class], version = 2)
abstract class RecentSearchDatabase : RoomDatabase() {
    abstract fun recentSearchListDao(): RecentSearchDao

    companion object {
        private var INSTANCE: RecentSearchDatabase? = null

        fun getInstance(context: Context): RecentSearchDatabase? {

            synchronized(this) {
                INSTANCE = Room.databaseBuilder(
                    context.applicationContext,
                    RecentSearchDatabase::class.java, "rcentmovie.db"
                )
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build()
            }

            return INSTANCE
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }


}