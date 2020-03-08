package io.github.sooakim.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import io.github.sooakim.data.local.converter.SADateTypeConverter
import io.github.sooakim.data.local.dao.SAMovieDao
import io.github.sooakim.data.local.model.SAMovieEntity

@Database(
    entities = [SAMovieEntity::class],
    exportSchema = false,
    version = 1
)
@TypeConverters(value = [SADateTypeConverter::class])
abstract class SADatabase : RoomDatabase() {
    abstract val movieDao: SAMovieDao

    object Factory {
        private const val DATABASE_NAME = "io.github.sooakim.local"

        fun create(applicationContext: Context): SADatabase {
            return Room.databaseBuilder(
                applicationContext.applicationContext,
                SADatabase::class.java,
                DATABASE_NAME
            ).build()
        }
    }
}