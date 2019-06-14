package org.study.kotlin.androidarchitecturestudy.adapter.recyclerviewadapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.item_ticker.view.*
import org.study.kotlin.androidarchitecturestudy.R
import org.study.kotlin.androidarchitecturestudy.api.model.TickerModel
import org.study.kotlin.androidarchitecturestudy.util.FormatUtil
import org.study.kotlin.androidarchitecturestudy.util.FormatUtil.accTradePriceFormat

class MainListAdapter :
    RecyclerView.Adapter<MainListAdapter.ItemViewHolder>() {
    var tickerList: ArrayList<TickerModel>? = null
    var itemViewHolder: ItemViewHolder? = null
    override fun onCreateViewHolder(viewGroup: ViewGroup, vewType: Int): ItemViewHolder {
        val v = LayoutInflater.from(viewGroup.context).inflate(R.layout.item_ticker, viewGroup, false)
        return ItemViewHolder(v)
    }

    override fun getItemCount(): Int = tickerList!!.size

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        itemViewHolder?.setData(holder,position,tickerList)

    }

    fun setList(list: ArrayList<TickerModel>) {
        tickerList?.clear()
        tickerList?.addAll(list)
        notifyDataSetChanged()
    }

    class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view){
//        val textview_item_market = view.textview_item_market
//        val textview_item_trade_price = view.textview_item_trade_price
//        val textview_item_change_rate = view.textview_item_change_rate
//        val textview_item_acc_trade_price_24h = view.textview_item_acc_trade_price_24h

        fun setData(holder: ItemViewHolder, position: Int, tickerList: ArrayList<TickerModel>?){
            tickerList!![position].let { item ->
                with(holder.itemView) {
                    textview_item_market.text = item.market.substringAfterLast("-")
                    if (item.market.substringBeforeLast("-") == "BTC" || item.market.substringBeforeLast("-") == "ETH") {
                        textview_item_trade_price.text = FormatUtil.floatingEightPointFormat(item.tradePrice)
                        textview_item_change_rate.text = FormatUtil.percentFormat(item.changeRate)
                        textview_item_acc_trade_price_24h.text =
                            FormatUtil.floatingThreePointFormat(item.accTradePrice24h)
                    } else if (item.market.substringBeforeLast("-") == "KRW") {
                        textview_item_trade_price.text = FormatUtil.commaDoubleFormat(item.tradePrice)
                        textview_item_change_rate.text = FormatUtil.percentFormat(item.changeRate)
                        textview_item_acc_trade_price_24h.text = accTradePriceFormat(item.accTradePrice24h, "KRW")
                    } else {
                        textview_item_trade_price.text = FormatUtil.usdtFloatingPointFormat(item.tradePrice)
                        textview_item_change_rate.text = FormatUtil.percentFormat(item.changeRate)
                        textview_item_acc_trade_price_24h.text = accTradePriceFormat(item.accTradePrice24h, "USDT")
                    }
                }
            }
        }
    }
}



