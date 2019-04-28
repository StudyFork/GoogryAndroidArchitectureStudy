package my.gong.studygong.viewcontrol

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import my.gong.studygong.R
import my.gong.studygong.data.model.Ticker
import my.gong.studygong.data.source.upbit.UpbitRepository
import my.gong.studygong.viewcontrol.adapter.CoinAdapter

class MainActivity : AppCompatActivity() {
    var coinList: MutableList<Ticker> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }

    private fun init(){
        getCoinData()
        recycler_main_ticker_list.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = CoinAdapter(this@MainActivity , coinList)
        }
    }

    private fun getCoinData(){
        UpbitRepository.getTickers(
            {
                it.map {
                    coinList.add(
                        Ticker(it.market)
                    )
                }
            },

            {
                Toast.makeText(this@MainActivity , " 에러 " , Toast.LENGTH_LONG).show()
            }

        )
    }
}