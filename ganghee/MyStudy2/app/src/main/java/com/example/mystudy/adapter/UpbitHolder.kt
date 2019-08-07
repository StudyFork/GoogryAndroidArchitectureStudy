package com.example.mystudy.adapter

import android.view.View
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.mystudy.data.FormatTickers
import kotlinx.android.synthetic.main.rv_item_list.view.*

class UpbitHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val coinName: TextView = itemView.tv_coin_name
    private val tradePrice: TextView = itemView.tv_trade_price
    private val signedChangeRate: TextView = itemView.tv_signed_change_rate
    private val accTradePrice24th: TextView = itemView.tv_acc_trade_price_24h

    fun bind(ticker: FormatTickers) {
        coinName.text = ticker.toMarket
        tradePrice.text = ticker.toTradePrice
        signedChangeRate.text = ticker.toSignedChangeRate
        signedChangeRate.setTextColor(
            ContextCompat.getColor(itemView.context, ticker.toRateColor)
        )
        accTradePrice24th.text = ticker.toAccTradePrice24h
    }
}