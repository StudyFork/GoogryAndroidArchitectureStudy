package com.architecture.study.view.coin.adapter.holder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.architecture.study.R
import com.architecture.study.data.model.Ticker
import com.architecture.study.databinding.ItemCoinBinding
import com.architecture.study.view.coin.adapter.CoinListAdapter

class CoinViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.item_coin, parent, false).apply {
        layoutParams = RecyclerView.LayoutParams(parent.measuredWidth, parent.measuredHeight / 10)
    }
) {
    private val binding: ItemCoinBinding =
        DataBindingUtil.bind(itemView)!!


    fun bind(ticker: Ticker, listener: CoinListAdapter.CoinItemRecyclerViewClickListener) {
        binding.ticker = ticker
        itemView.setOnClickListener {
            listener.onItemClicked(adapterPosition)
        }
        binding.run {
            tvCompareYesterday.setTextColor(
                ContextCompat.getColor(
                    itemView.context,
                    ticker.compareYesterdayTextColor
                )
            )
        }
    }
}