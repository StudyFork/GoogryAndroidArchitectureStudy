package com.aiden.aiden.architecturepatternstudy.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.aiden.aiden.architecturepatternstudy.api.model.TickerResponse

@Database(entities = [TickerResponse::class], version = 1)
abstract class UpbitDatabase : RoomDatabase() {

    abstract fun tickerDao(): TickerDao

}