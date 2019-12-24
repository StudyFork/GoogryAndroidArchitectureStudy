package com.egiwon.architecturestudy.data.source.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.egiwon.architecturestudy.data.source.local.model.Content
import com.egiwon.architecturestudy.data.source.local.model.ContentTypeConvertor

@Database(
    entities = [Content::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(ContentTypeConvertor::class)
abstract class ContentDataBase : RoomDatabase() {
    abstract fun contentDao(): ContentDao

    companion object {
        private var INSTANCE: ContentDataBase? = null

        private val lock = Any()

        fun getInstance(context: Context): ContentDataBase {
            synchronized(lock) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        ContentDataBase::class.java, "Contents.db"
                    ).build()
                }
                return INSTANCE!!
            }
        }
    }
}
