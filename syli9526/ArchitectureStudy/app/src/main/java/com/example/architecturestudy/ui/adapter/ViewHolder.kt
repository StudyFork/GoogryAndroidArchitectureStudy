package com.example.architecturestudy.ui.adapter

import android.content.Context
import android.view.View
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.architecturestudy.R
import com.example.architecturestudy.ui.CoinInfo

class ViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val tvCoinName = itemView.findViewById(R.id.tv_coin_name) as TextView
    private val tvPresentPrice = itemView.findViewById(R.id.tv_present_price) as TextView
    private val tvCompareWithYesterday = itemView.findViewById(R.id.tv_compare_yesterday) as TextView
    private val tvTransactionAmount = itemView.findViewById(R.id.tv_transaction_amount) as TextView

    fun bind(context: Context, coinInfo: CoinInfo, listener: CoinAdapter.CoinItemRecyclerViewClickListener) {
        itemView.setOnClickListener {
            listener.onItemClicked(adapterPosition)
        }

        tvCoinName.text = coinInfo.coinName
        tvPresentPrice.text = coinInfo.presentPrice
        tvCompareWithYesterday.text = coinInfo.compareWithYesterday
        tvCompareWithYesterday.setTextColor(ContextCompat.getColor(context, coinInfo.compareTextColor))
        tvTransactionAmount.text = coinInfo.transactionAmount
    }
}