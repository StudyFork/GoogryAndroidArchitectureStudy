package com.sangjin.newproject.data.source.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.sangjin.newproject.data.model.Movie

@Database(entities = arrayOf(Movie::class), version = 2)
abstract class RoomDb : RoomDatabase(){

    abstract val movieDao : MovieDao

    companion object{

        @Volatile
        private var INSTANCE: RoomDb? = null

        fun getInstance(context: Context): RoomDb {

            synchronized(this){
                var instance =
                    INSTANCE

                if(instance == null){
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        RoomDb::class.java,
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