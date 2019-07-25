package study.architecture.myarchitecture.data.source.local

import androidx.room.*
import io.reactivex.Single
import study.architecture.myarchitecture.data.model.UpbitMarket
import study.architecture.myarchitecture.data.model.UpbitTicker
import study.architecture.myarchitecture.data.source.UpbitDataSource

@Dao
interface UpbitDao : UpbitDataSource {

    @Query("SELECT * FROM markets")
    override fun getMarkets(): Single<List<UpbitMarket>>

    @Query("SELECT * FROM tickers WHERE market Like :marketKey")
    fun getTickers(marketKey: String): Single<List<UpbitTicker>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMarket(upbitMarket: UpbitMarket)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTicker(ticker: UpbitTicker)

    @Update
    fun updateMarket(upbitMarket: UpbitMarket)

    @Update
    fun updateTicker(ticker: UpbitTicker)

    @Query("DELETE FROM tickers WHERE market Like :marketKey")
    fun clearTickers(marketKey: String)

    @Query("DELETE FROM markets")
    fun clearMarkets()
}
