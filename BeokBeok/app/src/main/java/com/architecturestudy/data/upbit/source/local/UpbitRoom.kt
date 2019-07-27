package com.architecturestudy.data.upbit.source.local

import android.content.Context
import androidx.room.Room

object UpbitRoom {
    private var db: UpbitDatabase? = null

    fun getDao(context: Context?): UpbitTickerDao? {
        if (db == null) {
            context?.let {
                db = Room.databaseBuilder(
                    context.applicationContext,
                    UpbitDatabase::class.java,
                    "UpbitTicker.db"
                ).build()
            }
        }
        return db?.upbitTickerDao()
    }
}