package my.gong.studygong.mvp.view

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import my.gong.studygong.R
import my.gong.studygong.data.model.Ticker
import my.gong.studygong.mvp.Injection
import my.gong.studygong.mvp.adapter.CoinAdapter
import my.gong.studygong.mvp.presenter.CoinContract
import my.gong.studygong.mvp.presenter.CoinPresenter
import java.util.*

class CoinActivity : AppCompatActivity(), CoinContract.View {

    private lateinit var timer: Timer
    val presenter: CoinContract.Presenter by lazy {
        CoinPresenter(Injection.provideCoinRepository(), this@CoinActivity)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }

    override fun onStart() {
        super.onStart()
        timer = Timer()
        timer.scheduleAtFixedRate(object : TimerTask() {
            override fun run() {
                presenter.onStart()
            }
        }, 0, REPEAT_INTERVAL_MILLIS)
    }

    override fun onStop() {
        timer.cancel()
        super.onStop()
    }

    override fun showToast(msg: String) {
        Toast.makeText(this@CoinActivity, msg, Toast.LENGTH_SHORT).show()
    }

    override fun showTickers(ticker: List<Ticker>) {
        (recyclerview_main_ticker_list.adapter as CoinAdapter).refreshData(ticker)
    }

    private fun init() {
        recyclerview_main_ticker_list.adapter = CoinAdapter()
    }

    companion object {
        const val REPEAT_INTERVAL_MILLIS = 3000L
    }
}