package my.gong.studygong.viewcontrol

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import my.gong.studygong.R
import my.gong.studygong.data.model.enum.TickerCurrency
import my.gong.studygong.data.source.upbit.UpbitRepository
import my.gong.studygong.viewcontrol.adapter.CoinAdapter
import java.util.*

class MainActivity : AppCompatActivity() {

    private val REPEAT_INTERVAL_MILLIS = 3000L
    private var timer: Timer = Timer()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }

    override fun onResume() {
        super.onResume()
        getCoinData()
    }

    override fun onDestroy() {
        super.onDestroy()
        timer.cancel()
    }

    private fun init(){
        recycler_main_ticker_list.apply {
            adapter = CoinAdapter()
        }
    }

    private fun getCoinData(){
        timer.scheduleAtFixedRate(object : TimerTask() {
            override fun run() {
                UpbitRepository.getTickers(
                    tickerCurrency = TickerCurrency.KRW ,
                    success =   {
                                    runOnUiThread {
                                        (recycler_main_ticker_list.adapter as CoinAdapter).refreshData(it)
                                    }
                                },

                        fail =  {
                                    runOnUiThread {
                                        Toast.makeText(this@MainActivity, " $it ", Toast.LENGTH_LONG).show()
                                    }
                                }

                )
            }

        }, 0, REPEAT_INTERVAL_MILLIS)
    }
}