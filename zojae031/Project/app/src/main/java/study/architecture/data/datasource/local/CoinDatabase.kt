package study.architecture.data.datasource.local

import androidx.room.Database
import androidx.room.RoomDatabase
import study.architecture.data.datasource.local.dao.MarketDao
import study.architecture.data.datasource.local.dao.TickerDao
import study.architecture.data.entity.Market
import study.architecture.data.entity.Ticker

@Database(entities = [Market::class, Ticker::class], version = 1)
abstract class CoinDatabase : RoomDatabase() {

    abstract fun marketDao(): MarketDao
    abstract fun tickerDao(): TickerDao

}