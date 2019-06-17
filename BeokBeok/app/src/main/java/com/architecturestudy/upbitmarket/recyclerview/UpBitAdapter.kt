package com.architecturestudy.upbitmarket.recyclerview

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.architecturestudy.R
import com.architecturestudy.data.UpBitTicker
import java.text.DecimalFormat

class UpBitAdapter : RecyclerView.Adapter<UpBitAdapter.ViewHolder>() {
    var marketPrice: List<UpBitTicker>? = null

    fun setMarketPrices(marketPrice: List<UpBitTicker>) {
        this.marketPrice = marketPrice
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.rv_upbit_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = marketPrice?.size ?: 0

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        marketPrice?.get(position)?.let { holder.bind(it) }
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val coinName = itemView.findViewById<TextView>(R.id.tv_coin_name)
        private val currentPrice = itemView.findViewById<TextView>(R.id.tv_current_price)
        private val netChange = itemView.findViewById<TextView>(R.id.tv_net_change)
        private val tradingVal = itemView.findViewById<TextView>(R.id.tv_trading_value)

        fun bind(marketPrice: UpBitTicker) {
            coinName?.text =
                if (marketPrice.market?.contains("-")!!)
                    marketPrice.market.substringAfter("-")
                else
                    marketPrice.market
            currentPrice?.text = DecimalFormat("#,###").format(marketPrice.tradePrice)
            netChange?.text = String.format("%.2f", marketPrice.signedChangeRate?.times(100))
            tradingVal?.text = DecimalFormat("#,###M").format(marketPrice.accTradePrice24h?.div(1_000_000))
        }
    }
}