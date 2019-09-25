package study.architecture.data.datasource.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import study.architecture.data.entity.Market
import study.architecture.data.entity.Ticker
import study.architecture.data.datasource.local.dao.MarketDao
import study.architecture.data.datasource.local.dao.TickerDao

@Database(entities = [Market::class, Ticker::class], version = 1)
abstract class CoinDatabase : RoomDatabase() {

    abstract fun marketDao(): MarketDao
    abstract fun tickerDao(): TickerDao

    companion object {
        private var INSTANCE: CoinDatabase? = null
        fun getInstance(context: Context): CoinDatabase {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(
                    context.applicationContext,
                    CoinDatabase::class.java,
                    "coin"
                ).build()
            }
            return INSTANCE!!
        }
    }

}