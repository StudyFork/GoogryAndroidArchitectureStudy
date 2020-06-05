package io.github.jesterz91.study.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import io.github.jesterz91.study.data.local.dao.MovieDao
import io.github.jesterz91.study.data.local.model.MovieLocal

@Database(entities = [MovieLocal::class], version = 1)
abstract class MovieDatabase : RoomDatabase() {

    abstract fun movieDao(): MovieDao
}