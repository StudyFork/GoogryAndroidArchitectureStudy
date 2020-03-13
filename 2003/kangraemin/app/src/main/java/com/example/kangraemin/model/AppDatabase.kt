package com.example.kangraemin.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.kangraemin.model.local.datadao.MovieDao
import com.example.kangraemin.model.local.datadao.UserDao
import com.example.kangraemin.model.local.datamodel.Movie
import com.example.kangraemin.model.local.datamodel.User

@Database(entities = [User::class, Movie::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
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