package org.study.kotlin.androidarchitecturestudy.adapter.recyclerviewadapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.item_ticker.view.*
import org.study.kotlin.androidarchitecturestudy.R
import org.study.kotlin.androidarchitecturestudy.api.model.TickerModel
import org.study.kotlin.androidarchitecturestudy.util.FormatUtil

class MainListAdapter(var tickerList: ArrayList<TickerModel>) :
    RecyclerView.Adapter<MainListAdapter.ItemViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, vewType: Int): ItemViewHolder {
        val v = LayoutInflater.from(viewGroup?.context).inflate(R.layout.item_ticker, viewGroup, false)
        return ItemViewHolder(v)
    }

    override fun getItemCount(): Int = tickerList!!.size

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        tickerList!![position].let { item ->
            with(holder.itemView) {
                textview_item_market.text = item.market.substringAfterLast("-")
                if (item.market.substringBeforeLast("-") == "BTC" || item.market.substringBeforeLast("-") == "ETH") {
                    textview_item_trade_price.text = FormatUtil.tradPriceValueFloatingPointFormat(item.trade_price)
                    textview_item_change_rate.text = FormatUtil.numberPercentFormat(item.change_rate)
                    textview_item_acc_trade_price_24h.text = item.acc_trade_price_24h.toInt().toString()
                } else {
                    textview_item_trade_price.text = FormatUtil.numberCommaFormat(item.trade_price)
                    textview_item_change_rate.text = FormatUtil.numberPercentFormat(item.change_rate)
                    textview_item_acc_trade_price_24h.text = item.acc_trade_price_24h.toInt().toString()
                }
            }
        }

    }

    fun setList(list: ArrayList<TickerModel>) {
        tickerList?.clear()
        tickerList?.addAll(list)
        notifyDataSetChanged()
    }

    class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view)
}



