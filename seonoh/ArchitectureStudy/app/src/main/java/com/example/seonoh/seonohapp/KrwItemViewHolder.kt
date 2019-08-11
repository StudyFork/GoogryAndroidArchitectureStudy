package com.example.seonoh.seonohapp

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.seonoh.seonohapp.model.CurrentPriceInfoModel
import kotlinx.android.synthetic.main.krw_item.view.*
import java.text.DecimalFormat
import java.text.NumberFormat

class KrwItemViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
    fun bind(data: ArrayList<CurrentPriceInfoModel>,position : Int){
        with(data[position]){
            itemView.apply {
                coinName.text = market.substring(4,market.length)
                currentPrice.text = NumberFormat.getInstance().format(tradePrice.toInt())

                var nf = NumberFormat.getInstance()

                changeRate.text = nf.format(signedChangeRate)

                totalTradePrice.text = nf.format(accTradePrice_24h.toDouble())
//                totalTradePrice.text = String.format("%5d",accTradePrice_24h.toDouble())
            }
        }
    }
}