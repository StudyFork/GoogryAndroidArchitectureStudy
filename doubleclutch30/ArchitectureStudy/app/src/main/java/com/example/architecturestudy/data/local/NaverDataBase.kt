package com.example.architecturestudy.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.architecturestudy.data.local.Entity.BlogEntity
import com.example.architecturestudy.data.local.Entity.ImageEntity
import com.example.architecturestudy.data.local.Entity.KinEntity
import com.example.architecturestudy.data.local.Entity.MovieEntity
import com.example.architecturestudy.data.local.dao.BlogDao
import com.example.architecturestudy.data.local.dao.ImageDao
import com.example.architecturestudy.data.local.dao.KinDao
import com.example.architecturestudy.data.local.dao.MovieDao

@Database(entities = [MovieEntity::class, BlogEntity::class, KinEntity::class, ImageEntity::class], version = 1)
abstract class NaverDataBase : RoomDatabase() {

    abstract fun movieDao(): MovieDao
    abstract fun blogDao(): BlogDao
    abstract fun kinDao(): KinDao
    abstract fun imageDao(): ImageDao

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