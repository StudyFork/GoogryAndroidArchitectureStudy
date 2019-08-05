package study.architecture.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import study.architecture.model.dao.MarketDao
import study.architecture.model.dao.TickerDao
import study.architecture.model.entity.Market
import study.architecture.model.entity.Ticker

@Database(entities = [Market::class, Ticker::class], version = 1)
abstract class CoinDatabase : RoomDatabase() {

    abstract fun marketDao(): MarketDao
    abstract fun tickerDao(): TickerDao

    companion object {
        private var INSTANCE: CoinDatabase? = null
        fun getInstance(context: Context): CoinDatabase? {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(context.applicationContext, CoinDatabase::class.java, "coin").build()
            }
            return INSTANCE
        }
    }

}