package com.example.seonoh.seonohapp

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.example.seonoh.seonohapp.databinding.CoinFragmentBinding
import com.example.seonoh.seonohapp.model.CoinViewModel
import com.example.seonoh.seonohapp.model.CurrentPriceInfoModel
import com.example.seonoh.seonohapp.model.UseCoinModel
import com.example.seonoh.seonohapp.repository.CoinRepositoryImpl
import com.example.seonoh.seonohapp.util.CalculateUtils
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class CoinFragment : BaseFragment<CoinFragmentBinding>(
    R.layout.coin_fragment
) {

    private var fragmentAdapter = CoinAdapter()
    private var marketName: String? = null
    private val coinRepository = CoinRepositoryImpl()
    private val viewModel = CoinViewModel()

    private fun loadData(marketName: String) {
        compositeDisposable.add(
            coinRepository.sendCurrentPriceInfo(marketName)
                .map {
                    viewModel.coinItem.set(refineData(it))
                }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({}, {
                    Log.e("currentPriceInfo", "Fragment Network failed!! ${it.message}")
                })
        )
    }

    private fun refineData(result: List<CurrentPriceInfoModel>): List<UseCoinModel> {
        // 데이터 가공후 모델에 넣음.
        // signedChangeRate textcolor 처리때문에 viewholder에서 진행
        val marketType = if (result.isNotEmpty()) {
            result[0].market.substringBefore("-")
        } else ""

        return result.map {
            var rate = CalculateUtils.setTradeDiff(it.signedChangeRate)["rate"].toString()
            var color = CalculateUtils.setTradeDiff(it.signedChangeRate)["color"].toString().toInt()

            UseCoinModel(
                CalculateUtils.setMarketName(it.market),
                CalculateUtils.filterTrade(it.tradePrice),
                rate,
                color,
                it.accTradePrice24h?.let { CalculateUtils.setTradeAmount(marketType, it) ?: "0" }
            )
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.run {
            coinViewModel = viewModel
            krwRecyclerView.adapter = fragmentAdapter
        }
        marketName = arguments?.getString(MARKET)
        Log.e("market", "name : $marketName")
        marketName?.let {
            loadData(it)
        } ?: Toast.makeText(
            activity,
            resources.getString(R.string.empty_market_text),
            Toast.LENGTH_LONG
        ).show()
    }

    companion object {
        private const val MARKET = "market"

        fun newInstance(data: String): CoinFragment {
            val fragment = CoinFragment()
            val bundle = Bundle()
            bundle.putString(MARKET, data)
            fragment.arguments = bundle
            return fragment
        }
    }

}
