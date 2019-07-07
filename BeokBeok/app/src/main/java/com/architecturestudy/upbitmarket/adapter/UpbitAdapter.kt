package com.architecturestudy.upbitmarket.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.architecturestudy.R
import kotlinx.android.synthetic.main.fragment_upbit.view.*

class UpbitAdapter : RecyclerView.Adapter<UpbitAdapter.ViewHolder>() {
    private val marketPrice = mutableListOf<Map<String, String>>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder = ViewHolder(parent)

    override fun getItemCount(): Int = marketPrice.size

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) = marketPrice[position].let { holder.bind(it) }

    fun setMarketPrices(marketPrice: List<Map<String, String>>) {
        this.marketPrice.clear()
        this.marketPrice.addAll(marketPrice)
    }

    inner class ViewHolder(
        parent: ViewGroup
    ) : RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context)
            .inflate(R.layout.rv_upbit_item, parent, false)
    ) {
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