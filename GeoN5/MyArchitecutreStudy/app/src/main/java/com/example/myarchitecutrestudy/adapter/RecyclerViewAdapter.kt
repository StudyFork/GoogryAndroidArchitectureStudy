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
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_list_coin.view.*

class RecyclerViewAdapter(private val nameList: List<String>, private val context : Context) : RecyclerView.Adapter<CoinViewHolder>() {

    private var tickerList: ArrayList<Ticker> = ArrayList()

    fun setList(tickerList: ArrayList<Ticker>) {
        this.tickerList.clear()
        this.tickerList.addAll(tickerList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinViewHolder {
        val itemView: View = LayoutInflater.from(context).inflate(R.layout.item_list_coin, parent, false)
        return CoinViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return tickerList.size
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: CoinViewHolder, position: Int) {

        with(holder.containerView) {
            coinNameText.text = nameList[position].substring(nameList[position].lastIndexOf("-") + 1)

            currentPriceText.text = tickerList[position].tradePrice.toString()

            if((tickerList[position].signedChangeRate * 100).toString().length >= 4) {
                dayBeforeText.text = "${(tickerList[position].signedChangeRate * 100).toString().
                    substring(0, tickerList[position].signedChangeRate.toString().lastIndexOf(".") + 3)}%"
            }else{
                dayBeforeText.text = "${(tickerList[position].signedChangeRate * 100)}%"
            }

            if (dayBeforeText.text.toString().substring(0,dayBeforeText.text.toString().length-1).toDouble() > 0) {
                dayBeforeText.setTextColor(Color.RED)
            } else {
                dayBeforeText.setTextColor(Color.BLUE)
            }

            transactionPriceText.text = "${tickerList[position].accTradePrice24h.toInt()}M"
        }

    }
}

class CoinViewHolder(itemView: View) : AndroidExtensionsViewHolder(itemView)

abstract class AndroidExtensionsViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer