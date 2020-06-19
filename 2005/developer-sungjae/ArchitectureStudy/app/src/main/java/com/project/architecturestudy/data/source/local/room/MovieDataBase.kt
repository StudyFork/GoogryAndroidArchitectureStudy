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

        fun getInstance(context: Context): MovieDataBase {
            synchronized(MovieDataBase::class) {
                db = Room.databaseBuilder(context, MovieDataBase::class.java, "movielist.db")
                    .fallbackToDestructiveMigration()
                    .build()
            }
            return db
        }
    }
}