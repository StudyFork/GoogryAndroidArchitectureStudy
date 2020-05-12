package com.project.architecturestudy.data.source.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [MovieLocal::class], version = 1)
abstract class MovieRoomDataBase : RoomDatabase() {
    abstract fun getMovieDAO(): MovieItemDAO

    companion object {

        private var db: MovieRoomDataBase? = null

        fun getInstance(context: Context): MovieRoomDataBase? {
            if (db == null) {
                synchronized(MovieRoomDataBase::class) {
                    db = Room.databaseBuilder(context, MovieRoomDataBase::class.java, "movielist.db").fallbackToDestructiveMigration().build()
                }
            }

            return db
        }

        fun destoryInstance() {
            db = null
        }

    }

}