package com.example.architecturestudy.ui.adapter

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.architecturestudy.R
import com.example.architecturestudy.data.model.Ticker
import com.example.architecturestudy.util.setColor

class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val tvCoinName: TextView = itemView.findViewById(R.id.tv_coin_name)
    private val tvPresentPrice: TextView = itemView.findViewById(R.id.tv_present_price)
    private val tvCompareWithYesterday: TextView = itemView.findViewById(R.id.tv_compare_yesterday)
    private val tvTransactionAmount: TextView = itemView.findViewById(R.id.tv_transaction_amount)

    fun bind(ticker: Ticker) {

        with(ticker){
            tvCoinName.text = coinName
            tvPresentPrice.text = presentPrice
            tvCompareWithYesterday.text = signedChangeRate
            tvCompareWithYesterday.setColor(signedChangeRateColor)
            tvTransactionAmount.text = transactionAmount
        }

    }
}