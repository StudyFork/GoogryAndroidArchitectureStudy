package my.gong.studygong.viewcontrol

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import my.gong.studygong.R
import my.gong.studygong.data.model.Ticker
import my.gong.studygong.data.source.upbit.UpbitRepository
import my.gong.studygong.viewcontrol.adapter.CoinAdapter
import java.util.*

class MainActivity : AppCompatActivity() {

    var coinList: MutableList<Ticker> = mutableListOf()
    val minute = 30000L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }

    private fun init(){
        recycler_main_ticker_list.apply {
            adapter = CoinAdapter(this@MainActivity , coinList)
        }
        getCoinData()
    }

    private fun getCoinData(){
        Timer().scheduleAtFixedRate(object : TimerTask() {
            override fun run() {
                UpbitRepository.getTickers(
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

        }, 0, minute)

    }
}