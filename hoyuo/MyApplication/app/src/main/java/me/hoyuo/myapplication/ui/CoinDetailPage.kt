package me.hoyuo.myapplication.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_coin.acc_trade_price
import kotlinx.android.synthetic.main.activity_coin.acc_trade_price_24h
import kotlinx.android.synthetic.main.activity_coin.acc_trade_volume
import kotlinx.android.synthetic.main.activity_coin.acc_trade_volume_24h
import kotlinx.android.synthetic.main.activity_coin.change
import kotlinx.android.synthetic.main.activity_coin.change_price
import kotlinx.android.synthetic.main.activity_coin.change_rate
import kotlinx.android.synthetic.main.activity_coin.high_price
import kotlinx.android.synthetic.main.activity_coin.highest_52_week_date
import kotlinx.android.synthetic.main.activity_coin.highest_52_week_price
import kotlinx.android.synthetic.main.activity_coin.low_price
import kotlinx.android.synthetic.main.activity_coin.lowest_52_week_date
import kotlinx.android.synthetic.main.activity_coin.lowest_52_week_price
import kotlinx.android.synthetic.main.activity_coin.market
import kotlinx.android.synthetic.main.activity_coin.opening_price
import kotlinx.android.synthetic.main.activity_coin.prev_closing_price
import kotlinx.android.synthetic.main.activity_coin.signed_change_price
import kotlinx.android.synthetic.main.activity_coin.signed_change_rate
import kotlinx.android.synthetic.main.activity_coin.timestamp
import kotlinx.android.synthetic.main.activity_coin.trade_date
import kotlinx.android.synthetic.main.activity_coin.trade_date_kst
import kotlinx.android.synthetic.main.activity_coin.trade_price
import kotlinx.android.synthetic.main.activity_coin.trade_time
import kotlinx.android.synthetic.main.activity_coin.trade_time_kst
import kotlinx.android.synthetic.main.activity_coin.trade_timestamp
import kotlinx.android.synthetic.main.activity_coin.trade_volume
import me.hoyuo.myapplication.R
import me.hoyuo.myapplication.model.upbit.Ticker
import timber.log.Timber

class CoinDetailPage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        Timber.tag(TAG).d("onCreate")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coin)
        val data = intent.getSerializableExtra(KEY)
        data ?: run {
            Timber.tag(TAG).d("ticker is null")
            finish()
        }
        val ticker: Ticker = data as Ticker

        acc_trade_price.text = ticker.acc_trade_price.toString()
        acc_trade_price_24h.text = ticker.acc_trade_price_24h.toString()
        acc_trade_volume.text = ticker.acc_trade_volume.toString()
        acc_trade_volume_24h.text = ticker.acc_trade_volume_24h.toString()
        change.text = ticker.change
        change_price.text = ticker.change_price.toString()
        change_rate.text = ticker.change_rate.toString()
        high_price.text = ticker.high_price.toString()
        highest_52_week_date.text = ticker.highest_52_week_date
        highest_52_week_price.text = ticker.highest_52_week_price.toString()
        low_price.text = ticker.low_price.toString()
        lowest_52_week_date.text = ticker.lowest_52_week_date
        lowest_52_week_price.text = ticker.lowest_52_week_price.toString()
        market.text = ticker.market
        opening_price.text = ticker.opening_price.toString()
        prev_closing_price.text = ticker.prev_closing_price.toString()
        signed_change_price.text = ticker.signed_change_price.toString()
        signed_change_rate.text = ticker.signed_change_rate.toString()
        timestamp.text = ticker.timestamp.toString()
        trade_date.text = ticker.trade_date
        trade_date_kst.text = ticker.trade_date_kst
        trade_price.text = ticker.trade_price.toString()
        trade_time.text = ticker.trade_time
        trade_time_kst.text = ticker.trade_time_kst
        trade_timestamp.text = ticker.trade_timestamp.toString()
        trade_volume.text = ticker.trade_volume.toString()
    }

    companion object {
        val TAG: String = CoinDetailPage::class.java.simpleName
        const val KEY = "ticker"

        fun newIntent(context: Context, ticker: Ticker): Intent {
            return Intent(context, CoinDetailPage::class.java).apply {
                putExtra(KEY, ticker)
            }
        }
    }

}
