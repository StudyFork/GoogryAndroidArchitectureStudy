package com.example.seonoh.seonohapp

import android.graphics.Color
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.seonoh.seonohapp.model.CurrentPriceInfoModel
import com.facebook.stetho.inspector.helper.IntegerFormatter
import kotlinx.android.synthetic.main.krw_item.view.*
import java.text.DecimalFormat
import java.text.NumberFormat
import kotlin.math.roundToInt

const val MILLION = 1000000

const val KRW_TYPE = 0
const val BTC_TYPE = 1
const val ETH_TYPE = 2
const val USDT_TYPE = 3

class KrwItemViewHolder(itemView : View,val type : Int) : RecyclerView.ViewHolder(itemView){

    fun bind(data: ArrayList<CurrentPriceInfoModel>,position : Int){
        when(type){
            KRW_TYPE, USDT_TYPE -> {
                with(data[position]){
                    itemView.apply {
                        coinName.text = market.substring(4,market.length)
                        currentPrice.text = NumberFormat.getInstance().format(tradePrice.toInt())

                        var nf = NumberFormat.getInstance()

                        if(signedChangeRate.toString().contains("-")){
                            changeRate.setTextColor(Color.parseColor("#2222FF"))
                        }else{
                            changeRate.setTextColor(Color.parseColor("#FF3333"))
                        }
                        changeRate.text = String.format("%.2f",signedChangeRate*100) + " %"


                        totalTradePrice.text = nf.format((accTradePrice_24h.toDouble()/ MILLION).roundToInt()) + " M"
                    }
                }

            }

            BTC_TYPE -> {
                with(data[position]){
                    itemView.apply {
                        coinName.text = market.substring(4,market.length)
//                        currentPrice.text = ""+tradePrice.toFloat()
                        currentPrice.text = String().format("%.8f",tradePrice)


                        var nf = NumberFormat.getInstance()

                        if(signedChangeRate.toString().contains("-")){
                            changeRate.setTextColor(Color.parseColor("#2222FF"))
                        }else{
                            changeRate.setTextColor(Color.parseColor("#FF3333"))
                        }
                        changeRate.text = String.format("%.2f",signedChangeRate*100) + " %"


                        totalTradePrice.text = String.format("%.6s",accTradePrice_24h)


                    }
                }

            }

            ETH_TYPE -> {
                with(data[position]){
                    itemView.apply {
                        coinName.text = market.substring(4,market.length)
                        currentPrice.text = ""+tradePrice.toBigDecimal()

                        if(signedChangeRate.toString().contains("-")){
                            changeRate.setTextColor(Color.parseColor("#2222FF"))
                        }else{
                            changeRate.setTextColor(Color.parseColor("#FF3333"))
                        }

                        changeRate.text = String.format("%.2f",signedChangeRate*100) + " %"

                        totalTradePrice.text = accTradePrice_24h

                    }
                }
            }
        }

    }
}