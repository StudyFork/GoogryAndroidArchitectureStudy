package com.aiden.aiden.architecturepatternstudy.domain

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.aiden.aiden.architecturepatternstudy.api.model.TickerResponse
import com.aiden.aiden.architecturepatternstudy.domain.dao.TickerDao

@Database(entities = [TickerResponse::class], version = 1)
abstract class UpbitDatabase : RoomDatabase() {

    abstract fun tickerDao(): TickerDao

    companion object {

        private var instance: UpbitDatabase? = null

        operator fun invoke(context: Context) =
            instance ?: Room.databaseBuilder(
                context,
                UpbitDatabase::class.java, "upbit-db"
            )
                .fallbackToDestructiveMigration()
                .build()
                .apply { instance = this }

    }
}