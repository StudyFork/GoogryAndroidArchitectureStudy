package com.aiden.aiden.architecturepatternstudy.domain.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.aiden.aiden.architecturepatternstudy.domain.model.Ticker

@Dao
interface TickerDao {
    @Query("SELECT * FROM ticker")
    fun getAll(): List<Ticker>

    @Query("SELECT * FROM ticker WHERE market = :market LIMIT 1")
    fun getByMarket(market: String): Ticker

    @Insert(onConflict = REPLACE)
    fun insert(vararg users: Ticker)
}