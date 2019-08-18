package com.example.architecturestudy.ui.adapter

import android.content.Context
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.architecturestudy.R
import com.example.architecturestudy.ui.CoinInfo
import com.example.architecturestudy.util.filterTrade
import com.example.architecturestudy.util.setTradeAmount
import com.example.architecturestudy.util.setTradeDiff

class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val tvCoinName: TextView = itemView.findViewById(R.id.tv_coin_name)
    private val tvPresentPrice: TextView = itemView.findViewById(R.id.tv_present_price)
    private val tvCompareWithYesterday: TextView = itemView.findViewById(R.id.tv_compare_yesterday)
    private val tvTransactionAmount: TextView = itemView.findViewById(R.id.tv_transaction_amount)

    fun bind(coinInfo: CoinInfo) {

        tvCoinName.text = coinInfo.coinName
        tvPresentPrice.filterTrade(coinInfo.presentPrice)
        tvCompareWithYesterday.setTradeDiff(coinInfo.signedChangeRate)
        tvTransactionAmount.setTradeAmount(coinInfo.currencyType, coinInfo.transactionAmount)
    }
}