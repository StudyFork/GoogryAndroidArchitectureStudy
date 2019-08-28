package com.test.androidarchitecture


import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.test.androidarchitecture.adpter.TickerAdapter
import com.test.androidarchitecture.data.Ticker
import com.test.androidarchitecture.data.TickerFormat
import com.test.androidarchitecture.data.source.UpbitRepository
import com.test.androidarchitecture.network.RetrofitClient
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_coin.*
import java.text.DecimalFormat
import java.text.NumberFormat
import java.util.*


class TickerFragment : Fragment() {

    private val adapter by lazy { TickerAdapter() }
    private val upbitRepository: UpbitRepository by lazy { UpbitRepository }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_coin, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val marketSearch = arguments?.getString(MARKET_SEARCH)
        coin_recyclerView.adapter = this.adapter
        marketSearch?.let { loadCoinData(it) }
    }


    @SuppressLint("CheckResult")
    private fun loadCoinData(marketSearch: String) {
        upbitRepository.getTicker(marketSearch)
            .map { list: List<Ticker> ->
                list.map { getCoinFormat(it) }
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    adapter.setItem(it)
                }, {
                    Toast.makeText(context, it.message, Toast.LENGTH_SHORT).show()
                }
            )
    }

    private fun getCoinFormat(ticker: Ticker): TickerFormat {
        val marketName: String = ticker.market.substringAfter("-")

        val tradePrice: String = when {
            ticker.tradePrice < 1 -> String.format("%.8f", ticker.tradePrice)
            else -> NumberFormat.getNumberInstance(Locale.US).format(ticker.tradePrice)
        }

        val changeRate = """${String.format("%.2f", ticker.signedChangeRate * 100)}%"""

        val changeColor: Int = when (ticker.change) {
            "RISE" -> R.color.colorRed
            "EVEN" -> R.color.colorBlack
            "FALL" -> R.color.colorBlue
            else -> R.color.colorBlack
        }
        val df = DecimalFormat("#,###")
        val accTradePrice: String = when {
            ticker.accTradePrice24h > 1000000 -> df.format(ticker.accTradePrice24h / 1000000) + "M"
            ticker.accTradePrice24h > 1000 -> df.format(ticker.accTradePrice24h / 1000) + "K"
            else -> df.format(ticker.accTradePrice24h)
        }
        return TickerFormat(marketName, tradePrice, changeRate, changeColor, accTradePrice)
    }

    companion object {

        private const val MARKET_SEARCH: String = "marketSearch"

        fun getInstance(marketSearch: String): TickerFragment {
            val args = Bundle()
            args.putString(MARKET_SEARCH, marketSearch)
            val fragment = TickerFragment()
            fragment.arguments = args
            return fragment
        }
    }
}
