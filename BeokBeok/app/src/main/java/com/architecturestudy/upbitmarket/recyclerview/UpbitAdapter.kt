package com.architecturestudy.upbitmarket.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_upbit.view.*

class UpbitAdapter(
    @LayoutRes
    private val resource: Int
) : RecyclerView.Adapter<UpbitAdapter.ViewHolder>() {
    var marketPrice: List<Map<String, String>>? = null

    fun setMarketPrices(marketPrice: List<Map<String, String>>) {
        this.marketPrice = marketPrice
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(resource, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = marketPrice?.size ?: 0

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        marketPrice?.get(position)?.let { holder.bind(it) }
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val coinName = itemView.tv_coin_name
        private val currentPrice = itemView.tv_current_price
        private val netChange = itemView.tv_net_change
        private val tradingVal = itemView.tv_trading_value

        fun bind(marketPrice: Map<String, String>) {
            coinName.text = marketPrice["coinName"]
            currentPrice.text = marketPrice["currentPrice"]
            netChange.text = marketPrice["netChange"]
            tradingVal.text = marketPrice["tradingVal"]
        }
    }
}