package com.camai.archtecherstudy.source.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [RecentSearchNameList::class], version = 2)
abstract class RecentSearchListDatabase : RoomDatabase() {
    abstract fun recentSearchListDao(): RecentSearchListDao

    companion object {
        private var INSTANCE: RecentSearchListDatabase? = null

        fun getInstance(context: Context): RecentSearchListDatabase? {
            val tempInstance = INSTANCE

            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                INSTANCE = Room.databaseBuilder(
                    context.applicationContext,
                    RecentSearchListDatabase::class.java, "rcentmovie.db"
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