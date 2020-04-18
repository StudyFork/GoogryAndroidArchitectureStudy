package io.github.jesterz91.study.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import io.github.jesterz91.study.data.local.dao.MovieDao
import io.github.jesterz91.study.data.local.model.MovieLocal

@Database(entities = [MovieLocal::class], version = 1)
abstract class MovieDatabase : RoomDatabase() {

    abstract fun movieDao(): MovieDao

    companion object {

        @Volatile
        private var instance: MovieDatabase? = null

        fun getInstance(context: Context): MovieDatabase {
            synchronized(this) {
                return instance ?: run {
                    Room.inMemoryDatabaseBuilder(
                        context,
                        MovieDatabase::class.java
                    ).build().also {
                        instance = it
                    }
                }
            }
        }
    }
}