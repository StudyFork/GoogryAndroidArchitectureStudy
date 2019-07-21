package com.example.mystudy.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.mystudy.R
import com.example.mystudy.network.TickerResponse
import kotlinx.android.synthetic.main.rv_item_list.view.*
import org.jetbrains.anko.textColor

class RecyclerViewAdapter(var ctx: Context, var dataList: List<TickerResponse>) :
    RecyclerView.Adapter<RecyclerViewAdapter.Holder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view: View = LayoutInflater.from(ctx).inflate(R.layout.rv_item_list, parent, false)
        return Holder(view)
    }

    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(holder: Holder, position: Int) {
        //코인명
        holder.coin_name.text = dataList[position].market.split("-")[1]

        //현재가
        if (dataList[position].trade_price > 0.1) {
            holder.tv_trade_price.text = String.format("%,.0f", dataList[position].trade_price)
        } else {
            holder.tv_trade_price.text = String.format("%,.8f", dataList[position].trade_price)
        }

        //전일대비
        holder.signed_change_rate.text = String.format("%,.2f", dataList[position].signed_change_rate * 100) + "%"
        when {
            dataList[position].signed_change_rate > 0 -> holder.signed_change_rate.textColor =
                ContextCompat.getColor(ctx, R.color.diff_up)
            dataList[position].signed_change_rate < 0 -> holder.signed_change_rate.textColor =
                ContextCompat.getColor(ctx, R.color.diff_down)
            else -> holder.signed_change_rate.textColor = ContextCompat.getColor(ctx, R.color.black)
        }

        //거래대금
        when(dataList[position].market.split("-")[0]){
            "KRW" -> {
                holder.acc_trade_price_24th.text = String.format("%,.0f", dataList[position].acc_trade_volume_24h) + "M"
            }
            "USDT" -> {
                if(dataList[position].acc_trade_volume_24h > 1000000)
                holder.acc_trade_price_24th.text = String.format("%,.0f", dataList[position].acc_trade_volume_24h / 1000) + "k"
                else
                    holder.acc_trade_price_24th.text = String.format("%,.0f", dataList[position].acc_trade_volume_24h)
            }
            else -> holder.acc_trade_price_24th.text = String.format("%,.0f", dataList[position].acc_trade_volume_24h)
        }
    }

    inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var coin_name = itemView.tv_coin_name
        var tv_trade_price = itemView.tv_trade_price
        var signed_change_rate = itemView.tv_signed_change_rate
        var acc_trade_price_24th = itemView.tv_acc_trade_price_24h
    }
}