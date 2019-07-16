package com.aiden.aiden.architecturepatternstudy.domain.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.aiden.aiden.architecturepatternstudy.api.model.TickerResponse

@Dao
interface TickerDao {
    @Query("SELECT * FROM ticker")
    fun getAll(): List<TickerResponse>

    @Query("SELECT * FROM ticker WHERE market = :market")
    fun getByMarket(market: String): List<TickerResponse>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(tickerList: List<TickerResponse>)
}