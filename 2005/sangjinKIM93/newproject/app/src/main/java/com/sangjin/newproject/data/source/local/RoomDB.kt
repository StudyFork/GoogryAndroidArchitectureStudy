package com.sangjin.newproject.data.source.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.sangjin.newproject.data.model.Movie

@Database(entities = arrayOf(Movie::class), version = 1)
abstract class RoomDB : RoomDatabase(){

    abstract val movieDao : MovieDao

    companion object{

        @Volatile
        private var INSTANCE: RoomDB? = null

        fun getInstance(context: Context): RoomDB {

            synchronized(this){
                var instance =
                    INSTANCE

                if(instance == null){
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        RoomDB::class.java,
                        "movie_database"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }

                return instance
            }

        }

    }
}