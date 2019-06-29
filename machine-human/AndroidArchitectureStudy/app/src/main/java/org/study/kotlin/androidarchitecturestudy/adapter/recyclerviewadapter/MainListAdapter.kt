package org.study.kotlin.androidarchitecturestudy.adapter.recyclerviewadapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.item_ticker.view.*
import org.study.kotlin.androidarchitecturestudy.R
import org.study.kotlin.androidarchitecturestudy.api.model.TickerModel
import org.study.kotlin.androidarchitecturestudy.data.enum.TabTitle
import org.study.kotlin.androidarchitecturestudy.util.FormatUtil
import org.study.kotlin.androidarchitecturestudy.util.FormatUtil.accTradePriceFormat

class MainListAdapter : RecyclerView.Adapter<MainListAdapter.ItemViewHolder>() {
    private val tickerList = mutableListOf<TickerModel>()

    override fun onCreateViewHolder(viewGroup: ViewGroup, vewType: Int): ItemViewHolder {
        val v = LayoutInflater.from(viewGroup.context).inflate(R.layout.item_ticker, viewGroup, false)

        return ItemViewHolder(v)
    }

    override fun getItemCount(): Int = tickerList.size


    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.setData(tickerList[position])
    }

    fun setList(list: List<TickerModel>) {
        tickerList.run {
            clear()
            addAll(list)
        }
    }

    class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val tvItemMarket by lazy { view.textview_item_market }
        private val tvItemTradePrice by lazy { view.textview_item_trade_price }
        private val tvItemChangeRate by lazy { view.textview_item_change_rate }
        private val tvItemAccTradePrice by lazy { view.textview_item_acc_trade_price_24h }

        fun setData(item: TickerModel) {
            tvItemMarket.text = item.market.substringAfterLast("-")
            if (item.market.substringBeforeLast("-") == TabTitle.BTC.toString() || item.market.substringBeforeLast("-") == TabTitle.ETH.toString()) {
                tvItemTradePrice.text = FormatUtil.floatingEightPointFormat(item.tradePrice)
                tvItemChangeRate.text = FormatUtil.percentFormat(item.changeRate)
                tvItemAccTradePrice.text = FormatUtil.floatingThreePointFormat(item.accTradePrice24h)
            } else if (item.market.substringBeforeLast("-") == TabTitle.KRW.toString()) {
                tvItemTradePrice.text = FormatUtil.commaFormat(item.tradePrice)
                tvItemChangeRate.text = FormatUtil.percentFormat(item.changeRate)
                tvItemAccTradePrice.text = accTradePriceFormat(item.accTradePrice24h, TabTitle.KRW.toString())
            } else {
                tvItemTradePrice.text = FormatUtil.usdtFloatingPointFormat(item.tradePrice)
                tvItemChangeRate.text = FormatUtil.percentFormat(item.changeRate)
                tvItemAccTradePrice.text = accTradePriceFormat(item.accTradePrice24h, TabTitle.USDT.toString())
            }
        }
    }
}




