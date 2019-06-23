package com.aiden.aiden.architecturepatternstudy.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.aiden.aiden.architecturepatternstudy.R
import com.aiden.aiden.architecturepatternstudy.api.model.TickerModel
import com.aiden.aiden.architecturepatternstudy.data.enums.Market
import com.aiden.aiden.architecturepatternstudy.util.StringUtil
import kotlinx.android.synthetic.main.item_ticker.view.*
import java.math.BigDecimal

class TickerListAdapter : RecyclerView.Adapter<TickerListAdapter.ItemTickerViewHolder>() {

    private var tickerList = ArrayList<TickerModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemTickerViewHolder {
        val retView =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_ticker, parent, false)
        return ItemTickerViewHolder(retView)
    }

    override fun getItemCount(): Int = tickerList.size


    override fun onBindViewHolder(holder: ItemTickerViewHolder, position: Int) {
        holder.bind(tickerList[position])
    }

    fun setData(list: List<TickerModel>) {
        this.tickerList.clear()
        this.tickerList.addAll(list)
    }

    inner class ItemTickerViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        fun bind(tickerModel: TickerModel) {
            with(view) {
                // 코인 이름
                item_ticker_tv_coin_name.text = tickerModel.market.split("-")[1]
                // 현재 가격
                item_ticker_tv_now_price.text = if (tickerModel.market.startsWith(
                        Market.KRW.marketName,
                        true
                    )
                ) {
                    StringUtil.getKrwCommaPrice(BigDecimal(tickerModel.tradePrice))
                } else if (tickerModel.market.startsWith(
                        Market.BTC.marketName,
                        true
                    ) || tickerModel.market.startsWith(
                        Market.ETH.marketName,
                        true
                    )
                ) {
                    StringUtil.getBtcEthCommaPrice(tickerModel.tradePrice)
                } else {
                    StringUtil.getUsdtCommaPrice(tickerModel.tradePrice)
                }
                // 전일대비
                when {
                    tickerModel.prevClosingPrice > tickerModel.tradePrice -> {
                        item_ticker_tv_compare_before.setTextColor(context.getColor(R.color.blue))
                    }
                    tickerModel.prevClosingPrice < tickerModel.tradePrice -> {
                        item_ticker_tv_compare_before.setTextColor(context.getColor(R.color.red))
                    }
                    else -> {
                        item_ticker_tv_compare_before.setTextColor(context.getColor(R.color.black))
                    }
                }
                item_ticker_tv_compare_before.text =
                    StringUtil.getPercent(
                        tickerModel.prevClosingPrice,
                        tickerModel.tradePrice
                    )
                // 거래대금
                item_ticker_tv_total_deal_price.text =
                    if (tickerModel.market.startsWith(
                            Market.KRW.marketName,
                            true
                        )
                    ) {
                        StringUtil.getKrwTotalDealPrice(tickerModel.accTradePrice24h)
                    } else if (tickerModel.market.startsWith(
                            Market.BTC.marketName,
                            true
                        ) || tickerModel.market.startsWith(
                            Market.ETH.marketName,
                            true
                        )
                    ) {
                        StringUtil.getBtcEthTotalDealPrice(tickerModel.accTradePrice24h)
                    } else {
                        StringUtil.getUsdtTotalDealPrice(tickerModel.accTradePrice24h)
                    }
            }
        }
    }
}
