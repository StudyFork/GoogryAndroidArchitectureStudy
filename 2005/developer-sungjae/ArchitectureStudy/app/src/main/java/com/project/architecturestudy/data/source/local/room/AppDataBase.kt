package com.project.architecturestudy.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [MovieLocal::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun movieItemDAO(): MovieItemDAO
}