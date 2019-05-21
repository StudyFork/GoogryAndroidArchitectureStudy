package my.gong.studygong.mvp.view

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import my.gong.studygong.R
import my.gong.studygong.data.model.Ticker
import my.gong.studygong.mvp.Injection
import my.gong.studygong.mvp.adapter.CoinAdapter
import my.gong.studygong.mvp.presenter.coinlist.CoinListContract
import my.gong.studygong.mvp.presenter.coinlist.CoinListPresenter
import java.util.*

class CoinListActivity : AppCompatActivity(), CoinListContract.View {

    private var timer: Timer = Timer()
    private val presenter: CoinListContract.Presenter by lazy {
        CoinListPresenter(
            Injection.provideCoinRepository(),
            this@CoinListActivity
        )
    }
    private var coinMarket: String = "KRW"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }

    override fun onStart() {
        super.onStart()
        populateCoin(coinMarket)
    }

    override fun onStop() {
        timer.cancel()
        super.onStop()
    }

    override fun showToast(msg: String) {
        Toast.makeText(this@CoinListActivity, msg, Toast.LENGTH_SHORT).show()
    }

    override fun showTickers(ticker: List<Ticker>) {
        (recyclerview_main_ticker_list.adapter as CoinAdapter).refreshData(ticker)
    }

    override fun showSearchDialog(coin: String) {
        val coinMarketDialog = CoinDetailDialog()

        coinMarketDialog.arguments =
                Bundle().apply {
                    putString(COIN_DETAIL, edit_search_ticker.text.toString())
                }

        coinMarketDialog.show(supportFragmentManager, null)
    }

    private fun init() {
        recyclerview_main_ticker_list.adapter = CoinAdapter()

        linear_coin_markets.setOnClickListener {
            val coinMarketDialog = CoinMarketDialog()

            coinMarketDialog.dialogCallBackListener = {
                txt_select_coin_market.text = it
                coinMarket = it
                populateCoin(coinMarket)
            }

            coinMarketDialog.show(supportFragmentManager, null)
        }

        btn_search.setOnClickListener {
            presenter.searchCoin(edit_search_ticker.text.toString())
        }
    }

    private fun populateCoin(coinMarket: String) {
        timer.cancel()
        timer = Timer()
        timer.scheduleAtFixedRate(object : TimerTask() {
            override fun run() {
                presenter.populateCoinData(coinMarket)
            }
        }, 0, REPEAT_INTERVAL_MILLIS)
    }

    companion object {
        const val REPEAT_INTERVAL_MILLIS = 3000L
        const val COIN_DETAIL = "COIN_DETAIL"
    }
}