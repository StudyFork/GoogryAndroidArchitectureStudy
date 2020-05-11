package com.project.architecturestudy.data.source.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [MovieRoomDataBase::class], version = 1)
abstract class MovieRoomDataBase : RoomDatabase() {
    abstract fun getMovieDAO(): MovieItemDAO

    companion object {

        private var INSTANCE: MovieRoomDataBase? = null
        fun getInstance(context: Context): MovieRoomDataBase? {
            if (INSTANCE == null) {
                synchronized(MovieRoomDataBase::class) {
                    INSTANCE = Room.databaseBuilder(context, MovieRoomDataBase::class.java, "movie.db").build()
                }
            }

            return INSTANCE
        }

    }

}