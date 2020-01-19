package com.example.archstudy.data.source.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [MovieData::class, SearchWord::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    // 데이터베이스와 연결되는 DAO
    abstract fun localMovieDao(): MovieDataDao

    abstract fun searchWordDao(): SearchWordDao

    companion object {
        // singleton instance
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase? {
            if (INSTANCE == null) {
                // 동시에 두개의 객체가 생기는 것을 방지
                synchronized(AppDatabase::class) {
                    INSTANCE = Room.databaseBuilder(context, AppDatabase::class.java, "localItem")
                        .build()
                }
            }
            return INSTANCE
        }
    }
}