package study.architecture.model.datasource

import android.content.Context
import io.reactivex.Single
import study.architecture.model.CoinDatabase
import study.architecture.model.vo.Market

class LocalDataSource private constructor(private val context: Context) {
    private val db by lazy { CoinDatabase.getInstance(context) }
    private val marketDao = db!!.marketDao()

    fun getMarket(): Single<List<Market>> = marketDao.selectAll()


    companion object {
        private var INSTANCE: LocalDataSource? = null
        fun getInstance(context: Context): LocalDataSource {
            if (INSTANCE == null) {
                INSTANCE = LocalDataSource(context)
            }
            return INSTANCE!!
        }
    }


}