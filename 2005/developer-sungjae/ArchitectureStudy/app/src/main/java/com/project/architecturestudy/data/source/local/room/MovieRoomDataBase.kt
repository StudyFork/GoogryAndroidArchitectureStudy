package com.project.architecturestudy.data.source.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [MovieLocalItem::class], version = 1)
abstract class MovieRoomDataBase : RoomDatabase() {

    abstract fun getMovieDao(): MovieItemDao

    companion object {
        private lateinit var db: MovieRoomDataBase

        fun getInstance(context: Context): MovieRoomDataBase {
            synchronized(MovieRoomDataBase::class) {
                db = Room.databaseBuilder(context, MovieRoomDataBase::class.java, "movielist.db")
                    .fallbackToDestructiveMigration()
                    .build()
            }
            return db
        }
    }
}