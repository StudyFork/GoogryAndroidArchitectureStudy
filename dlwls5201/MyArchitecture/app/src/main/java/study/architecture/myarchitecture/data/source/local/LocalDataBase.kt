package study.architecture.myarchitecture.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import study.architecture.myarchitecture.data.model.UpbitMarket
import study.architecture.myarchitecture.data.model.UpbitTicker

@Database(
    entities = [UpbitMarket::class, UpbitTicker::class],
    version = 1,
    exportSchema = false
)
abstract class LocalDataBase : RoomDatabase() {

    abstract fun getUpbitDao(): UpbitDao
}
