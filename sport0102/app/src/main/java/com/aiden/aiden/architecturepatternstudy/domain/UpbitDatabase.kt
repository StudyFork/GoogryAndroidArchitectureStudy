package com.aiden.aiden.architecturepatternstudy.domain

import androidx.room.Database
import androidx.room.RoomDatabase
import com.aiden.aiden.architecturepatternstudy.domain.dao.TickerDao
import com.aiden.aiden.architecturepatternstudy.domain.model.Ticker

@Database(entities = [Ticker::class], version = 1)
abstract class UpbitDatabase : RoomDatabase() {
    abstract fun tickerDao(): TickerDao
}