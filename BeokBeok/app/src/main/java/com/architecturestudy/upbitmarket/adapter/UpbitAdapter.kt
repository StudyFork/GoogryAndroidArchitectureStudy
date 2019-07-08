package com.architecturestudy.upbitmarket.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.architecturestudy.R
import com.architecturestudy.databinding.RvUpbitItemBinding

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
        private val binding: RvUpbitItemBinding = DataBindingUtil.bind(itemView)!!

        fun bind(marketPrice: Map<String, String>) {
            binding.tvCoinName.text = marketPrice["coinName"]
            binding.tvCurrentPrice.text = marketPrice["currentPrice"]
            binding.tvNetChange.text = marketPrice["netChange"]
            binding.tvTradingValue.text = marketPrice["tradingVal"]
        }
    }
}