package com.example.architecturestudy.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.architecturestudy.data.local.Dao.BlogDao
import com.example.architecturestudy.data.local.Dao.MovieDao
import com.example.architecturestudy.data.local.Entity.BlogEntity
import com.example.architecturestudy.data.local.Entity.MovieEntity

@Database(entities = [MovieEntity::class, BlogEntity::class], version = 1)
abstract class NaverDataBase : RoomDatabase() {

    abstract fun movieDao(): MovieDao
    abstract fun blogDao(): BlogDao

    companion object {
        private var INSTANCE: NaverDataBase? = null

        private val lock = Any()

        fun getInstance(context: Context): NaverDataBase {
            synchronized(lock) {
                if(INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                            NaverDataBase::class.java, "naver.db")
                           .build()
                }
            }
            return INSTANCE!!
        }
    }
}