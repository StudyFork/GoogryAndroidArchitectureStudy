package com.byiryu.local.model.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.byiryu.local.model.MovieItem

@Database(entities = [MovieItem::class], version = 1)
abstract class LocalDataBase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
}