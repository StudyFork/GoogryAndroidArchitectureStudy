package com.test.androidarchitecture.adpter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.test.androidarchitecture.R
import com.test.androidarchitecture.data.TickerFormat
import com.test.androidarchitecture.ext.*
import kotlinx.android.synthetic.main.item_coin.view.*
import kotlin.collections.ArrayList

class TickerAdapter : RecyclerView.Adapter<TickerAdapter.CoinViewHolder>() {

    private val tickerFormatList = mutableListOf<TickerFormat>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinViewHolder {
        return CoinViewHolder(parent)
    }

    override fun getItemCount() = tickerFormatList.size


    override fun onBindViewHolder(holder: CoinViewHolder, position: Int) {
        holder.onBindView(tickerFormatList[position])
    }


    fun setItem(tickerList: List<TickerFormat>) {
        this.tickerFormatList.clear()
        this.tickerFormatList.addAll(tickerList)
        notifyDataSetChanged()
    }


    class CoinViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_coin, parent, false)
    ) {

        private val coinNameTextView = itemView.item_coin_name_text

        private val coinNowPriceTextView = itemView.item_coin_now_price_text

        private val coinPreParePercentTextView = itemView.item_coin_prepare_percent_text

        private val coinDealPriceTextView = itemView.item_coin_deal_price_text

        fun onBindView(tickerFormat: TickerFormat) {
            coinNameTextView.text = tickerFormat.marketName
            coinNowPriceTextView.text = tickerFormat.tradePrice
            coinPreParePercentTextView.text = tickerFormat.changeRate
            coinPreParePercentTextView.setTextColorRes(tickerFormat.changeColor)
            coinDealPriceTextView.text = tickerFormat.accTradePrice
        }
    }


}