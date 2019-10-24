package study.architecture.coinjob

import android.util.Log
import androidx.lifecycle.MutableLiveData
import io.reactivex.android.schedulers.AndroidSchedulers
import study.architecture.base.BaseViewModel
import study.architecture.data.entity.ProcessingTicker
import study.architecture.data.repository.Repository
import study.architecture.util.TextUtil
import java.util.concurrent.TimeUnit

class CoinViewModel(
    private val repository: Repository
) : BaseViewModel() {
    private lateinit var marketName: String

    val lists = MutableLiveData<List<ProcessingTicker>>()
    val loadingState = MutableLiveData<Boolean>()

    fun getMarkets(index: CoinFragment.FragIndex) {
        repository.getMarkets()
            .map { list ->
                list.filter { filterData
                    ->
                    filterData.market.startsWith(index.name)
                }
                    .joinToString(",") { separateData ->
                        separateData.market
                    }
            }
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { loadingState.value = true }
            .subscribe(
                {
                    marketName = it
                    tickerRequest()
                },
                {
                    Log.e("CoinViewModelError", it.message)
                }).also { compositeDisposable.add(it) }
    }

    private fun tickerRequest() {
        if (::marketName.isInitialized) {
            repository.getTickers(marketName)
                .repeatWhen { it.delay(8, TimeUnit.SECONDS) }
                .doOnRequest { loadingState.value = false }
                .map { list ->
                    list.map { data ->
                        ProcessingTicker(
                            TextUtil.getMarketName(data.market),
                            TextUtil.getTradePrice(data.tradePrice),
                            TextUtil.getChangeRate(data.signedChangeRate),
                            TextUtil.getAccTradePrice24h(data.accTradePrice24h),
                            TextUtil.getColorState(data.signedChangeRate)
                        )
                    }
                }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { pLists ->
                        if (!repository.checkNetwork()) Log.e("데이터 연결x", "데이터 최신화 필요")
                        lists.value = pLists
                    },
                    { e ->
                        Log.e("CoinViewModel", e.message)
                    }
                ).also { compositeDisposable.add(it) }
        }
    }

    override fun onResume() {

        tickerRequest()

    }

}
