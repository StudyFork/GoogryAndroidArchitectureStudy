package com.example.myarchitecutrestudy.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.myarchitecutrestudy.R
import com.example.myarchitecutrestudy.model.Ticker
import kotlinx.android.synthetic.main.item_list_coin.view.*

class RecyclerViewAdapter(var nameList: List<String>, var tickerList : List<Ticker>, var context : Context) : RecyclerView.Adapter<CoinViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinViewHolder {
        val itemView: View = LayoutInflater.from(context).inflate(R.layout.item_list_coin, parent, false)
        return CoinViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return tickerList.size
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: CoinViewHolder, position: Int) {
        holder.coinName.text = nameList[position].substring(nameList[position].lastIndexOf("-") + 1)
        holder.currentPrice.text = tickerList[position].trade_price.toString()

        if (tickerList[position].signed_change_rate.toString().length > 4) {
            holder.dayBefote.text = "${tickerList[position].signed_change_rate.toString().
                substring(0, tickerList[position].signed_change_rate.toString().lastIndexOf(".") + 3)}%"
        } else {
            holder.dayBefote.text = tickerList[position].signed_change_rate.toString()
        }
        if (tickerList[position].signed_change_rate > 0) {
            holder.dayBefote.setTextColor(Color.RED)
        } else {
            holder.dayBefote.setTextColor(Color.BLUE)
        }

        holder.transactionPrice.text = "${tickerList[position].acc_trade_price_24h.toInt()}M"

    }

}

class CoinViewHolder(itemView : View):RecyclerView.ViewHolder(itemView){
    var coinName = itemView.coinNameText!!
    var currentPrice = itemView.currentPriceText!!
    var dayBefote = itemView.dayBeforeText!!
    var transactionPrice = itemView.transactionPriceText!!
}