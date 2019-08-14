package com.example.seonoh.seonohapp

import android.util.Log
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.seonoh.seonohapp.model.CurrentPriceInfoModel
import com.example.seonoh.seonohapp.util.*
import kotlinx.android.synthetic.main.coin_item.view.*
import java.text.DecimalFormat
import java.text.NumberFormat
import kotlin.math.roundToInt


const val KRW_TYPE = 0
const val BTC_TYPE = 1
const val ETH_TYPE = 2
const val USDT_TYPE = 3




class CoinItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(data: ArrayList<CurrentPriceInfoModel>, position: Int) {
        with(data[position]) {
            itemView.apply {
                coinName.setMarketName(market)
                currentPrice.filterTrade(tradePrice)
                changeRate.setTradeDiff(signedChangeRate)
                totalTradePrice.setTradeAmount(accTradePrice_24h)
            }
        }
    }
}
