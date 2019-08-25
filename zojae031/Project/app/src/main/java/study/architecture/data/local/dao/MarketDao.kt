package study.architecture.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import io.reactivex.Single
import study.architecture.data.entity.Market

@Dao
interface MarketDao : BaseDao<Market> {
    @Query("Select * from market")
    fun selectAll(): Single<List<Market>>
}