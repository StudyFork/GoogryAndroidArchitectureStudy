package com.jay.architecturestudy.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.jay.architecturestudy.data.database.dao.BlogDao
import com.jay.architecturestudy.data.database.dao.ImageDao
import com.jay.architecturestudy.data.database.dao.KinDao
import com.jay.architecturestudy.data.database.dao.MovieDao
import com.jay.architecturestudy.data.database.entity.BlogEntity
import com.jay.architecturestudy.data.database.entity.ImageEntity
import com.jay.architecturestudy.data.database.entity.KinEntity
import com.jay.architecturestudy.data.database.entity.MovieEntity

@Database(
    entities = [BlogEntity::class, ImageEntity::class, KinEntity::class, MovieEntity::class],
    version = 1
)

abstract class SearchHistoryDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao

    abstract fun blogDao(): BlogDao

    abstract fun imageDao(): ImageDao

    abstract fun kinDao(): KinDao
}
