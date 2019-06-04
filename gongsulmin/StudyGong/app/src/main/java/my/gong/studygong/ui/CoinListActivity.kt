package my.gong.studygong.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import my.gong.studygong.Injection
import my.gong.studygong.R
import my.gong.studygong.adapter.CoinAdapter
import my.gong.studygong.viewmodel.CoinViewModel
import java.util.*

class CoinListActivity : AppCompatActivity() {

    private var timer: Timer = Timer()
    private var coinMarket: String = "KRW"
    private val coinMarketDialog = CoinMarketDialog()

    private val coinViewModel: CoinViewModel by lazy {
        CoinViewModel(Injection.provideCoinRepository())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }

    override fun onStart() {
        super.onStart()
        loadCoin(coinMarket)
    }

    override fun onStop() {
        timer.cancel()
        super.onStop()
    }

    private fun init() {
        recyclerview_main_ticker_list.adapter = CoinAdapter()

        linear_coin_markets.setOnClickListener {
            coinMarketDialog.show(supportFragmentManager, null)
        }

        btn_search.setOnClickListener {
            val coinSearchDialog = CoinSearchDialog()

            coinSearchDialog.arguments =
                Bundle().apply {
                    putString(COIN_DETAIL, edit_search_ticker.text.toString())
                }

            coinSearchDialog.show(supportFragmentManager, null)
        }

        coinViewModel.tickerLoadedListener = {
            (recyclerview_main_ticker_list.adapter as CoinAdapter).refreshData(it)
        }

        coinViewModel.errorLoadedListener = { msg ->
            Toast.makeText(this@CoinListActivity, msg, Toast.LENGTH_SHORT).show()
        }
    }

    private fun loadCoin(coinMarket: String) {
        timer.cancel()
        timer = Timer()
        timer.scheduleAtFixedRate(object : TimerTask() {
            override fun run() {
                coinViewModel.loadTickerList(coinMarket)
            }
        }, 0, REPEAT_INTERVAL_MILLIS)
    }

    fun onClickCoinMarketItem(market: String) {
        txt_select_coin_market.text = market
        coinMarket = market
        loadCoin(coinMarket)
    }

    companion object {
        const val REPEAT_INTERVAL_MILLIS = 3000L
        const val COIN_DETAIL = "COIN_DETAIL"
    }
}