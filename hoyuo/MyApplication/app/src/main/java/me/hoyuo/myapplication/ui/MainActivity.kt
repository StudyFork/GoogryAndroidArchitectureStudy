package me.hoyuo.myapplication.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.itemListView
import me.hoyuo.myapplication.R
import me.hoyuo.myapplication.model.upbit.Ticker
import me.hoyuo.myapplication.ui.adapter.ItemAdapter
import timber.log.Timber

class MainActivity : AppCompatActivity() {

    private lateinit var itemAdapter: ItemAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var ticker = Ticker(market = "KRW-BTC",
                trade_date = "20190429",
                trade_time = "164855",
                trade_date_kst = "20190430",
                trade_time_kst = "014855",
                trade_timestamp = 1556556535000,
                opening_price = 6138000,
                high_price = 6159000,
                low_price = 5963000,
                trade_price = 6049000,
                prev_closing_price = 6138000,
                change = "FALL",
                change_price = 89000,
                change_rate = 0.0144998371,
                signed_change_price = -89000,
                signed_change_rate = -0.0144998371,
                trade_volume = 0.23967589,
                acc_trade_price = 30157511065.90749,
                acc_trade_price_24h = 36568849971.23742,
                acc_trade_volume = 4959.27076794,
                acc_trade_volume_24h = 6006.47936362,
                highest_52_week_price = 10963000,
                highest_52_week_date = "2018-05-06",
                lowest_52_week_price = 3562000,
                lowest_52_week_date = "2018-12-15",
                timestamp = 1556556535779)

        setAdapter()
        itemAdapter.loadData(listOf(ticker))
    }

    private fun setAdapter() {
        itemAdapter = ItemAdapter().apply {
            setItemClickListener(object : ItemAdapter.ItemClickListener {
                override fun onItemClick(ticker: Ticker) {
                    Timber.d("Item Click")

                    Timber.d("$ticker")
                }
            })
        }

        itemListView.apply {
            adapter = itemAdapter
            layoutManager = LinearLayoutManager(context)
        }
    }
}
