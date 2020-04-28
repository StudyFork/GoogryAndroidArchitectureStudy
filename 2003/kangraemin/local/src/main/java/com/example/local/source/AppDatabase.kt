package com.example.local.source

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.local.model.Auth
import com.example.local.model.Movie

@Database(entities = [Auth::class, Movie::class], version = 1)
internal abstract class AppDatabase : RoomDatabase() {
    abstract fun authDao(): AuthDao
    abstract fun movieDao(): MovieDao

    companion object {
        private var db: AppDatabase? = null

        @JvmStatic
        @Synchronized
        fun getInstance(context: Context): AppDatabase {
            if (db == null) {
                db = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "local"
                ).build()
            }
            return db!!
        }
    }
}