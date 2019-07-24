package com.example.mystudy.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.mystudy.R
import com.example.mystudy.network.TickerResponse
import kotlinx.android.synthetic.main.rv_item_list.view.*
import org.jetbrains.anko.textColor

class TickerAdapter(private var ctx: Context, private var dataList: List<TickerResponse>) :
    RecyclerView.Adapter<TickerAdapter.Holder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view: View = LayoutInflater.from(ctx).inflate(R.layout.rv_item_list, parent, false)
        return Holder(view)
    }

    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(holder: Holder, position: Int) {
        //코인명
        holder.coinName.text = dataList[position].market.split("-")[1]

        //현재가
        if (dataList[position].tradePrice > 0.1) {
            holder.tvTradePrice.text = String.format("%,.0f", dataList[position].tradePrice)
        } else {
            holder.tvTradePrice.text = String.format("%,.8f", dataList[position].tradePrice)
        }

        //전일대비
        holder.signedChangeRate.text = String.format("%,.2f", dataList[position].signedChangeRate * 100) + "%"
        when {
            dataList[position].signedChangeRate > 0 -> holder.signedChangeRate.textColor =
                ContextCompat.getColor(ctx, R.color.diff_up)
            dataList[position].signedChangeRate < 0 -> holder.signedChangeRate.textColor =
                ContextCompat.getColor(ctx, R.color.diff_down)
            else -> holder.signedChangeRate.textColor = ContextCompat.getColor(ctx, R.color.black)
        }

        //거래대금
        when(dataList[position].market.split("-")[0]){
            "KRW" -> {
                holder.accTradePrice24th.text = String.format("%,.0f", dataList[position].accTradePrice24h / 1000000) + "M"
            }
            "USDT" -> {
                if(dataList[position].accTradePrice24h > 1000000)
                holder.accTradePrice24th.text = String.format("%,.0f", dataList[position].accTradePrice24h / 1000) + "k"
                else
                    holder.accTradePrice24th.text = String.format("%,.0f", dataList[position].accTradePrice24h)
            }
            else -> holder.accTradePrice24th.text = String.format("%,.3f", dataList[position].accTradePrice24h)
        }
    }

    inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var coinName: TextView = itemView.tv_coin_name
        var tvTradePrice: TextView = itemView.tv_trade_price
        var signedChangeRate: TextView = itemView.tv_signed_change_rate
        var accTradePrice24th: TextView = itemView.tv_acc_trade_price_24h
    }
}
