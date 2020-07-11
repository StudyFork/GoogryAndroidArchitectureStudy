package com.project.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.project.local.model.MovieLocalItem
import com.project.local.model.MovieTypeConverter

@Database(entities = [MovieLocalItem::class], version = 1)
@TypeConverters(MovieTypeConverter::class)
abstract class MovieDataBase : RoomDatabase() {

    abstract fun getMovieDao(): MovieItemDao

    companion object {
        private lateinit var db: MovieDataBase
        const val DB_NAME = "movielist.db"

        fun getInstance(context: Context): MovieDataBase {
            synchronized(MovieDataBase::class) {
                db = Room.databaseBuilder(context, MovieDataBase::class.java, DB_NAME)
                    .fallbackToDestructiveMigration()
                    .build()
            }
            return db
        }
    }
}