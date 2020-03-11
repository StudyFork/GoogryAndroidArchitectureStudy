package com.byiryu.study.model.source.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.byiryu.study.model.entity.MovieItem

@Database(entities = [MovieItem::class], version = 1)
abstract class LocalDataBase : RoomDatabase(){
    abstract fun movieDao() : MovieDao

    companion object{
        private var ins : LocalDataBase? = null

        fun getInstance(context:Context) : LocalDataBase?{
            if(ins == null){
                synchronized(MovieDao::class){
                    ins = Room.databaseBuilder((context.applicationContext),
                        LocalDataBase::class.java, "Movie.db")
                        .fallbackToDestructiveMigration()
                        .build()
                }
            }
            return ins
        }

        fun destoryIns(){
            ins = null
        }
    }
}