package com.project.architecturestudy.data.source.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [MovieLocalItem::class], version = 1)
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