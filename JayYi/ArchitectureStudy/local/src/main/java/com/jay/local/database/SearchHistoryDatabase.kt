package com.jay.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.jay.local.dao.BlogDao
import com.jay.local.dao.ImageDao
import com.jay.local.dao.KinDao
import com.jay.local.dao.MovieDao
import com.jay.local.model.BlogEntity
import com.jay.local.model.ImageEntity
import com.jay.local.model.KinEntity
import com.jay.local.model.MovieEntity

@Database(
    entities = [BlogEntity::class, ImageEntity::class, KinEntity::class, MovieEntity::class],
    version = 1
)

internal abstract class SearchHistoryDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao

    abstract fun blogDao(): BlogDao

    abstract fun imageDao(): ImageDao

    abstract fun kinDao(): KinDao
}
