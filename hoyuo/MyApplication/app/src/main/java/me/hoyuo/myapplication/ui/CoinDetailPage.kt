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
        val ticker = intent.getParcelableExtra<Ticker>(KEY)
        ticker ?: run {
            Timber.tag(TAG).e("ticker is null")
            finish()
        }

        acc_trade_price.text = ticker.accTradePrice.toString()
        acc_trade_price_24h.text = ticker.accTradePrice24h.toString()
        acc_trade_volume.text = ticker.accTradeVolume.toString()
        acc_trade_volume_24h.text = ticker.accTradeVolume24h.toString()
        change.text = ticker.change
        change_price.text = ticker.changePrice.toString()
        change_rate.text = ticker.changeRate.toString()
        high_price.text = ticker.highPrice.toString()
        highest_52_week_date.text = ticker.highest52WeekDate
        highest_52_week_price.text = ticker.highest52WeekPrice.toString()
        low_price.text = ticker.lowPrice.toString()
        lowest_52_week_date.text = ticker.lowest52WeekDate
        lowest_52_week_price.text = ticker.lowest52WeekPrice.toString()
        market.text = ticker.market
        opening_price.text = ticker.openingPrice.toString()
        prev_closing_price.text = ticker.prevClosingPrice.toString()
        signed_change_price.text = ticker.signedChangePrice.toString()
        signed_change_rate.text = ticker.signedChangeRate.toString()
        timestamp.text = ticker.timestamp.toString()
        trade_date.text = ticker.tradeDate
        trade_date_kst.text = ticker.tradeDateKst
        trade_price.text = ticker.tradePrice.toString()
        trade_time.text = ticker.tradeTime
        trade_time_kst.text = ticker.tradeTimeKst
        trade_timestamp.text = ticker.tradeTimestamp.toString()
        trade_volume.text = ticker.tradeVolume.toString()
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
