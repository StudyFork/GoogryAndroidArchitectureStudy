package com.hwaniiidev.architecture.data.source.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.hwaniiidev.architecture.model.Item

@Database(entities = [Item::class],version = 1)
abstract class MoviesDB : RoomDatabase(){
    abstract fun moviesDao(): MoviesDao

    companion object{
        private var instance: MoviesDB? = null

        fun getInstance(context:Context): MoviesDB?{
            if(instance == null){
                synchronized(MoviesDB::class){
                    instance = Room.databaseBuilder(context.applicationContext,
                    MoviesDB::class.java,"movies.db")
                        .fallbackToDestructiveMigration()
                        .build()
                }
            }
            return instance
        }


    }
    fun detroyInstance(){
        instance = null
    }
}