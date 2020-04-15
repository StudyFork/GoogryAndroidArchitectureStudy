package io.github.jesterz91.study.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import io.github.jesterz91.study.data.local.dao.MovieDao
import io.github.jesterz91.study.data.local.model.MovieLocal
import io.github.jesterz91.study.data.local.model.MovieSearchQuery
import io.github.jesterz91.study.presentation.constant.Constant

@Database(
    entities = [MovieLocal::class, MovieSearchQuery::class],
    version = 1,
    exportSchema = false
)
abstract class MovieDatabase : RoomDatabase() {

    abstract fun movieDao(): MovieDao

    companion object {

        @Volatile
        private var INSTANCE: MovieDatabase? = null

        fun getInstance(context: Context): MovieDatabase {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        MovieDatabase::class.java,
                        Constant.MOVIE_DATABASE_NAME
                    ).build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}