package com.cnm.homework.data.source.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [LocalEntity::class], version = 1, exportSchema = false)
@TypeConverters(LocalConverters::class)
abstract class LocalDatabase : RoomDatabase() {
    abstract fun localDao(): LocalDao

}
