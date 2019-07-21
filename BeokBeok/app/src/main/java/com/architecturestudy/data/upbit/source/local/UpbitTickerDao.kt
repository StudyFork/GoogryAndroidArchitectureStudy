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

    @Query("SELECT * FROM upbitTicker WHERE market LIKE :type || '%' ORDER BY market")
    fun sortMarket(type: String): List<UpbitTicker>

    @Query("SELECT * FROM upbitTicker WHERE market LIKE :type || '%' ORDER BY market DESC")
    fun sortMarketByDESC(type: String): List<UpbitTicker>

    @Query("SELECT * FROM upbitticker WHERE trade_price LIKE :type || '%' ORDER BY trade_price")
    fun sortTradePrice(type: String): List<UpbitTicker>

    @Query("SELECT * FROM upbitTicker WHERE trade_price LIKE :type || '%' ORDER BY trade_price DESC")
    fun sortTradePriceByDESC(type: String): List<UpbitTicker>

    @Query("SELECT * FROM upbitTicker WHERE signed_change_rate LIKE :type || '%' ORDER BY signed_change_rate")
    fun sortSignedChangeRate(type: String): List<UpbitTicker>

    @Query("SELECT * FROM upbitTicker WHERE signed_change_rate LIKE :type || '%' ORDER BY signed_change_rate DESC")
    fun sortSignedChangeRateByDESC(type: String): List<UpbitTicker>

    @Query("SELECT * FROM upbitTicker WHERE acc_trade_price_24h LIKE :type || '%' ORDER BY acc_trade_price_24h")
    fun sortAccTradePrice24h(type: String): List<UpbitTicker>

    @Query("SELECT * FROM upbitTicker WHERE acc_trade_price_24h LIKE :type || '%' ORDER BY acc_trade_price_24h DESC")
    fun sortAccTradePrice24hByDESC(type: String): List<UpbitTicker>

}