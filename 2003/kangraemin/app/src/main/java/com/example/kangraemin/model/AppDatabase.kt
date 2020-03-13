package com.example.kangraemin.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.kangraemin.model.local.datadao.AuthDao
import com.example.kangraemin.model.local.datadao.MovieDao
import com.example.kangraemin.model.local.datamodel.Auth
import com.example.kangraemin.model.local.datamodel.Movie

@Database(entities = [Auth::class, Movie::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun authDao(): AuthDao
    abstract fun movieDao(): MovieDao

    companion object {
        private var db: AppDatabase? = null

        @JvmStatic
        fun getInstance(context: Context): AppDatabase {
            if (db == null) {
                synchronized(AppDatabase::class) {
                    if (db == null) {
                        db = Room.databaseBuilder(
                            context.applicationContext,
                            AppDatabase::class.java,
                            "local"
                        ).build()
                    }
                }
            }
            return db!!
        }
    }
}