package com.egiwon.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.egiwon.local.model.Content
import com.egiwon.local.model.ContentTypeConvertor

@Database(
    entities = [Content::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(ContentTypeConvertor::class)
abstract class ContentDataBase : RoomDatabase() {
    abstract fun contentDao(): ContentDao

    companion object {
        const val DB_NAME = "Contents.db"
    }
}