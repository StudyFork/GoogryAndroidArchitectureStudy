package com.aiden.aiden.architecturepatternstudy.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.aiden.aiden.architecturepatternstudy.R
import com.aiden.aiden.architecturepatternstudy.api.model.TickerModel
import com.aiden.aiden.architecturepatternstudy.data.enums.Market
import com.aiden.aiden.architecturepatternstudy.util.*
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
            // 코인 이름
            item_ticker_tv_coin_name.text = tickerList[position].market.split("-")[1]
            // 현재 가격
            item_ticker_tv_now_price.text = if (tickerList[position].market.startsWith(Market.KRW.marketName, true)) {
                getKrwCommaPrice(tickerList[position].tradePrice)
            } else if (tickerList[position].market.startsWith(
                    Market.BTC.marketName,
                    true
                ) || tickerList[position].market.startsWith(Market.ETH.marketName, true)
            ) {
                getBtcEthCommaPrice(tickerList[position].tradePrice)
            } else {
                getUsdtCommaPrice(tickerList[position].tradePrice)
            }
            // 전일대비
            when {
                tickerList[position].prevClosingPrice > tickerList[position].tradePrice -> {
                    item_ticker_tv_compare_before.setTextColor(context.getColor(R.color.blue))
                }
                tickerList[position].prevClosingPrice < tickerList[position].tradePrice -> {
                    item_ticker_tv_compare_before.setTextColor(context.getColor(R.color.red))
                }
                else -> {
                    item_ticker_tv_compare_before.setTextColor(context.getColor(R.color.black))
                }
            }
            item_ticker_tv_compare_before.text =
                getPercent(tickerList[position].prevClosingPrice, tickerList[position].tradePrice)
            // 거래대금
            item_ticker_tv_total_deal_price.text =
                if (tickerList[position].market.startsWith(Market.KRW.marketName, true)) {
                    getKrwTotalDealPrice(tickerList[position].accTradePrice24h)
                } else if (tickerList[position].market.startsWith(
                        Market.BTC.marketName,
                        true
                    ) || tickerList[position].market.startsWith(Market.ETH.marketName, true)
                ) {
                    getBtcEthTotalDealPrice(tickerList[position].accTradePrice24h)
                } else {
                    getUsdtTotalDealPrice(tickerList[position].accTradePrice24h)
                }
        }
    }

    fun setData(list: ArrayList<TickerModel>) {
        tickerList = list
    }

    inner class ItemTickerViewHolder(view: View) : RecyclerView.ViewHolder(view)
}
