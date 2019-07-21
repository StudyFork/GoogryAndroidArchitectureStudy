package com.architecturestudy.data.upbit.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.architecturestudy.data.upbit.UpbitTicker

@Database(
    entities = [UpbitTicker::class],
    version = 1,
    exportSchema = false
)
abstract class UpbitDatabase : RoomDatabase() {

    abstract fun upbitTickerDao(): UpbitTickerDao
}