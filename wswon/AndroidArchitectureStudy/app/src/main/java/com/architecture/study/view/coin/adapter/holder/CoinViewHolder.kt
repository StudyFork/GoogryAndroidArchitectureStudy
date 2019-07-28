package com.architecture.study.view.coin.adapter.holder

import android.content.Context
import android.view.View
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.architecture.study.R
import com.architecture.study.view.coin.adapter.CoinListAdapter
import com.architecture.study.data.model.Ticker

class CoinViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val tvCoinName = itemView.findViewById(R.id.coin_name_tv) as TextView
    private val tvNowPrice = itemView.findViewById(R.id.now_price_tv) as TextView
    private val tvCompareYesterday = itemView.findViewById(R.id.compare_yesterday_tv) as TextView
    private val tvTransactionAmount = itemView.findViewById(R.id.transaction_amount_tv) as TextView
    fun bind(ticker: Ticker, listener: CoinListAdapter.CoinItemRecyclerViewClickListener) {
        itemView.setOnClickListener {
            listener.onItemClicked(adapterPosition)
        }
        tvCoinName.text = ticker.coinName
        tvNowPrice.text = ticker.nowPrice
        tvCompareYesterday.text = ticker.compareYesterday
        tvCompareYesterday.setTextColor(ContextCompat.getColor(itemView.context, ticker.compareYesterdayTextColor))
        tvTransactionAmount.text = ticker.transactionAmount
    }
}