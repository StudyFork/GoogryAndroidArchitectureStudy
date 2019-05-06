package my.gong.studygong.mvp.view

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import my.gong.studygong.R
import my.gong.studygong.data.model.Ticker
import my.gong.studygong.mvp.presenter.CoinContract
import my.gong.studygong.mvp.presenter.CoinPresenter
import my.gong.studygong.viewcontrol.adapter.CoinAdapter
import java.util.*

class CoinActivity: AppCompatActivity() , CoinContract.View {

    private val REPEAT_INTERVAL_MILLIS = 3000L
    private var timer: Timer = Timer()
    override lateinit var presenter: CoinContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()

        presenter = CoinPresenter(this@CoinActivity)
    }

    override fun onStart() {
        super.onStart()
        presenter.onStart()
    }

    override fun onDestroy() {
        super.onDestroy()
        timer.cancel()
    }

    override fun showToast(msg: String) {
        Toast.makeText(this@CoinActivity , msg , Toast.LENGTH_SHORT).show()
    }

    override fun showTickers(ticker: List<Ticker>) {
        (recycler_main_ticker_list.adapter as CoinAdapter).refreshData(ticker)
    }

    private fun init(){
        recycler_main_ticker_list.apply {
            adapter = CoinAdapter()
        }
    }
}