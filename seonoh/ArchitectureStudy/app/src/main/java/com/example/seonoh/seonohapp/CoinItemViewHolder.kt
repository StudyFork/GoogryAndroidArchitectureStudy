package com.example.seonoh.seonohapp

import android.view.View
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.seonoh.seonohapp.model.UseCoinModel

class CoinItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val coinNameTv: TextView = itemView.findViewById(R.id.coinName)

    private val currentPriceTv: TextView = itemView.findViewById(R.id.currentPrice)

    private val changeRateTv: TextView = itemView.findViewById(R.id.changeRate)

    private val totalTradePriceTv: TextView = itemView.findViewById(R.id.totalTradePrice)

    fun bind(data: UseCoinModel) {
        coinNameTv.text = data.market
        currentPriceTv.text = data.tradePrice
        changeRateTv.run {
            text = data.signedChangeRate["rate"].toString()
            setTextColor(ContextCompat.getColor(itemView.context,data.signedChangeRate["color"].toString().toInt()))
        }
        totalTradePriceTv.text = String.format(itemView.context.getString(data.accTradePrice_24h["format"].toString().toInt()),data.accTradePrice_24h["price"])
    }
}
