package com.test.androidarchitecture


import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.test.androidarchitecture.adpter.CoinAdapter
import com.test.androidarchitecture.data.Coin
import com.test.androidarchitecture.data.CoinFormat
import com.test.androidarchitecture.network.RetrofitClient
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_coin.*
import java.text.DecimalFormat
import java.text.NumberFormat
import java.util.*


class CoinFragment : Fragment() {

    private val adapter by lazy { CoinAdapter() }
    private val retrofitService by lazy { RetrofitClient.getInstance().retrofitService }

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
        retrofitService.loadCoinData(marketSearch)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { list: List<Coin> ->
                    val formatList = list.map {
                        getCoinFormat(it)
                    }
                    adapter.addItem(formatList)
                }, { t: Throwable ->
                    Toast.makeText(context, t.message, Toast.LENGTH_SHORT).show()
                })
    }

    private fun getCoinFormat(coin: Coin): CoinFormat {
        val marketName: String = coin.market.substringAfter("-")

        val tradePrice: String = when {
            coin.tradePrice < 1 -> String.format("%.8f", coin.tradePrice)
            else -> NumberFormat.getNumberInstance(Locale.US).format(coin.tradePrice)
        }

        val changeRate = """${String.format("%.2f", coin.signedChangeRate * 100)}%"""

        val changeColor: Int = when (coin.change) {
            "RISE" -> R.color.colorRed
            "EVEN" -> R.color.colorBlack
            "FALL" -> R.color.colorBlue
            else -> R.color.colorBlack
        }
        val df = DecimalFormat("#,###")
        val accTradePrice: String = when {
            coin.accTradePrice24h > 1000000 -> df.format(coin.accTradePrice24h / 1000000) + "M"
            coin.accTradePrice24h > 1000 -> df.format(coin.accTradePrice24h / 1000) + "K"
            else -> df.format(coin.accTradePrice24h)
        }
        return CoinFormat(marketName, tradePrice, changeRate, changeColor, accTradePrice)
    }

    companion object {

        private const val MARKET_SEARCH: String = "marketSearch"

        fun getInstance(marketSearch: String): CoinFragment {
            val args = Bundle()
            args.putString(MARKET_SEARCH, marketSearch)
            val fragment = CoinFragment()
            fragment.arguments = args
            return fragment
        }
    }
}
