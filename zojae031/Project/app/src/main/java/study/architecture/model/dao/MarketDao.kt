package study.architecture.model.dao

import androidx.room.Dao
import androidx.room.Query
import io.reactivex.Single
import study.architecture.model.entity.Market

@Dao
interface MarketDao : BaseDAO<Market> {
    @Query("Select * from market")
    fun selectAll(): Single<List<Market>>
}