package com.example.seonoh.seonohapp

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.seonoh.seonohapp.model.CurrentPriceInfoModel
import kotlinx.android.synthetic.main.coin_item.view.*
import java.text.NumberFormat
import kotlin.math.roundToInt

const val MILLION = 1000000
const val THOUSAND = 1000

const val KRW_TYPE = 0
const val BTC_TYPE = 1
const val ETH_TYPE = 2
const val USDT_TYPE = 3

class CoinItemViewHolder(itemView: View, val type: Int) : RecyclerView.ViewHolder(itemView) {

    fun bind(data: ArrayList<CurrentPriceInfoModel>, position: Int) {
        with(data[position]) {
            itemView.apply {
                if (signedChangeRate.toString() == "0.00") {
                    changeRate.setTextColor(resources.getColor(R.color.seonohBlack))
                    return@with
                }

                if (signedChangeRate.toString().contains("-")) {
                    changeRate.setTextColor(resources.getColor(R.color.seonohBlue))
                } else {
                    changeRate.setTextColor(resources.getColor(R.color.seonohRed))
                }

                when (type) {
                    KRW_TYPE -> {
                        coinName.text = market.substring(4, market.length)
                        currentPrice.text = NumberFormat.getInstance().format(tradePrice.toInt())
                        var nf = NumberFormat.getInstance()
                        changeRate.text = String.format("%.2f", signedChangeRate * 100) + " %"
                        totalTradePrice.text = nf.format((accTradePrice_24h.toDouble() / MILLION).roundToInt()) + " M"

                    }

                    BTC_TYPE -> {
                        coinName.text = market.substring(4, market.length)
                        currentPrice.text = "" + tradePrice.toBigDecimal()
                        changeRate.text = String.format("%.2f", signedChangeRate * 100) + " %"
                        totalTradePrice.text = String.format("%.6s", accTradePrice_24h)
                    }

                    ETH_TYPE -> {
                        coinName.text = market.substring(4, market.length)
                        currentPrice.text = "" + tradePrice.toBigDecimal()
                        changeRate.text = String.format("%.2f", signedChangeRate * 100) + " %"
                        totalTradePrice.text = String.format("%.6s", accTradePrice_24h)
                    }

                    USDT_TYPE -> {
                        coinName.text = market.substring(5, market.length)
                        currentPrice.text = "" + tradePrice.toBigDecimal()
                        changeRate.text = String.format("%.2f", signedChangeRate * 100) + " %"
                        var nf = NumberFormat.getInstance()

                        if (accTradePrice_24h.toDouble() > MILLION) {
                            totalTradePrice.text =
                                nf.format((accTradePrice_24h.toDouble() / THOUSAND).roundToInt()) + " K"
                        } else {
                            totalTradePrice.text = nf.format((accTradePrice_24h.toDouble()).roundToInt())
                        }

                    }
                }
            }
        }


    }
}