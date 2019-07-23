package com.architecturestudy.data.upbit.source.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.architecturestudy.data.upbit.UpbitTicker

@Dao
interface UpbitTickerDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTicker(ticker: UpbitTicker)

    @Query("SELECT * FROM upbitTicker ORDER BY market")
    fun sortMarket(): List<UpbitTicker>

    @Query("SELECT * FROM upbitTicker ORDER BY market DESC")
    fun sortMarketByDESC(): List<UpbitTicker>

    @Query("SELECT * FROM upbitticker ORDER BY trade_price")
    fun sortTradePrice(): List<UpbitTicker>

    @Query("SELECT * FROM upbitTicker ORDER BY trade_price DESC")
    fun sortTradePriceByDESC(): List<UpbitTicker>

    @Query("SELECT * FROM upbitTicker ORDER BY signed_change_rate")
    fun sortSignedChangeRate(): List<UpbitTicker>

    @Query("SELECT * FROM upbitTicker ORDER BY signed_change_rate DESC")
    fun sortSignedChangeRateByDESC(): List<UpbitTicker>

    @Query("SELECT * FROM upbitTicker ORDER BY acc_trade_price_24h")
    fun sortAccTradePrice24h(): List<UpbitTicker>

    @Query("SELECT * FROM upbitTicker ORDER BY acc_trade_price_24h DESC")
    fun sortAccTradePrice24hByDESC(): List<UpbitTicker>

}