package com.example.architecturestudy.ui.adapter

import android.content.Context
import android.view.View
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.architecturestudy.R
import com.example.architecturestudy.ui.CoinInfo

class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val tvCoinName: TextView = itemView.findViewById(R.id.tv_coin_name)
    private val tvPresentPrice: TextView = itemView.findViewById(R.id.tv_present_price)
    private val tvCompareWithYesterday: TextView = itemView.findViewById(R.id.tv_compare_yesterday)
    private val tvTransactionAmount: TextView = itemView.findViewById(R.id.tv_transaction_amount)

    fun bind(context: Context, coinInfo: CoinInfo) {

        tvCoinName.text = coinInfo.coinName
        tvPresentPrice.text = coinInfo.presentPrice
        tvCompareWithYesterday.text = coinInfo.compareWithYesterday
        tvCompareWithYesterday.setTextColor(ContextCompat.getColor(context, coinInfo.compareTextColor))
        tvTransactionAmount.text = coinInfo.transactionAmount
    }
}