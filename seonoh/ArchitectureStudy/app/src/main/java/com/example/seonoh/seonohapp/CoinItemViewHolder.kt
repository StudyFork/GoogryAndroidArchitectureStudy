package com.example.seonoh.seonohapp

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.seonoh.seonohapp.model.CurrentPriceInfoModel
import com.example.seonoh.seonohapp.util.filterTrade
import com.example.seonoh.seonohapp.util.setMarketName
import com.example.seonoh.seonohapp.util.setTradeAmount
import com.example.seonoh.seonohapp.util.setTradeDiff


const val KRW_TYPE = 0
const val BTC_TYPE = 1
const val ETH_TYPE = 2
const val USDT_TYPE = 3


class CoinItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val coinNameTv: TextView = itemView.findViewById(R.id.coinName)

    private val currentPriceTv: TextView = itemView.findViewById(R.id.currentPrice)

    private val changeRateTv: TextView = itemView.findViewById(R.id.changeRate)

    private val totalTradePriceTv: TextView = itemView.findViewById(R.id.totalTradePrice)

    fun bind(data: CurrentPriceInfoModel) {
        coinNameTv.setMarketName(data.market)
        currentPriceTv.filterTrade(data.tradePrice)
        changeRateTv.setTradeDiff(data.signedChangeRate)
        totalTradePriceTv.setTradeAmount(data.accTradePrice_24h)

    }
}
