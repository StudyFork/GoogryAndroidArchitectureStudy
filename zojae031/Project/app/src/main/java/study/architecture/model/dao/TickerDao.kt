package study.architecture.model.dao

import androidx.room.Dao
import androidx.room.Query
import io.reactivex.Single
import study.architecture.model.entity.Ticker

@Dao
interface TickerDao : BaseDao<Ticker> {
    @Query("Select * from ticker")
    fun selectAll(): Single<MutableList<Ticker>>

    @Query("Select * from ticker where market Like :market")
    fun selectTicker(market: String): Single<MutableList<Ticker>>
}