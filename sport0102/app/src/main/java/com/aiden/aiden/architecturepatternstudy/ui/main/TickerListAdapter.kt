package com.aiden.aiden.architecturepatternstudy.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.aiden.aiden.architecturepatternstudy.R
import com.aiden.aiden.architecturepatternstudy.api.model.TickerModel
import kotlinx.android.synthetic.main.item_ticker.view.*

class TickerListAdapter : RecyclerView.Adapter<TickerListAdapter.ItemTickerViewHolder>() {

    private var tickerList = ArrayList<TickerModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemTickerViewHolder {
        val retView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_ticker, parent, false)
        return ItemTickerViewHolder(retView)
    }

    override fun getItemCount(): Int {
        return tickerList.size
    }

    override fun onBindViewHolder(holder: ItemTickerViewHolder, position: Int) {
        with(holder.itemView) {
            item_ticker_tv_coin_name.text = tickerList[position].market.split("-")[1]
            item_ticker_tv_now_price.text = tickerList[position].trade_price.toString()
            item_ticker_tv_compare_before.text = tickerList[position].signed_change_rate.toString()
            item_ticker_tv_total_deal_price.text = tickerList[position].acc_trade_price.toString()
        }
    }

    fun setData(list: ArrayList<TickerModel>) {
        tickerList = list
    }

    inner class ItemTickerViewHolder(view: View) : RecyclerView.ViewHolder(view)
}
