package com.example.seonoh.seonohapp

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.seonoh.seonohapp.model.UseCoinModel
import com.example.seonoh.seonohapp.util.setTradeDiff

class CoinItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val coinNameTv: TextView = itemView.findViewById(R.id.coinName)

    private val currentPriceTv: TextView = itemView.findViewById(R.id.currentPrice)

    private val changeRateTv: TextView = itemView.findViewById(R.id.changeRate)

    private val totalTradePriceTv: TextView = itemView.findViewById(R.id.totalTradePrice)

    fun bind(data: UseCoinModel) {
        coinNameTv.text = data.market
        currentPriceTv.text = data.tradePrice
        changeRateTv.setTradeDiff(data.signedChangeRate)
        totalTradePriceTv.text = data.accTradePrice_24h

    }
}
