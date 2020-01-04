package com.jay.architecturestudy.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.jay.architecturestudy.data.database.dao.BlogDao
import com.jay.architecturestudy.data.database.dao.ImageDao
import com.jay.architecturestudy.data.database.dao.KinDao
import com.jay.architecturestudy.data.database.dao.MovieDao
import com.jay.architecturestudy.data.database.entity.BlogItemEntity
import com.jay.architecturestudy.data.database.entity.ImageItemEntity
import com.jay.architecturestudy.data.database.entity.KinItemEntity
import com.jay.architecturestudy.data.database.entity.MovieItemEntity

@Database(
    entities = [BlogItemEntity::class, ImageItemEntity::class, KinItemEntity::class, MovieItemEntity::class],
    version = 1
)

abstract class SearchHistoryDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao

    abstract fun blogDao(): BlogDao

    abstract fun imageDao(): ImageDao

    abstract fun kinDao(): KinDao

    companion object {
        @Volatile
        private var INSTANCE: SearchHistoryDatabase? = null

        fun getInstance(context: Context): SearchHistoryDatabase =
            INSTANCE ?: synchronized(this) {
                buildDatabase(context).also { INSTANCE = it }
            }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                SearchHistoryDatabase::class.java,
                "search_history.db"
            )
                .build()

    }
}
