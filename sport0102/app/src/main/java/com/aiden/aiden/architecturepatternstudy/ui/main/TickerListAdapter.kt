package com.aiden.aiden.architecturepatternstudy.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.aiden.aiden.architecturepatternstudy.R
import com.aiden.aiden.architecturepatternstudy.data.model.TickerModel
import kotlinx.android.synthetic.main.item_ticker.view.*

class TickerListAdapter : RecyclerView.Adapter<TickerListAdapter.ItemTickerViewHolder>() {

    private var tickerList = mutableListOf<TickerModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemTickerViewHolder {
        val retView =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_ticker, parent, false)
        return ItemTickerViewHolder(retView)
    }

    override fun getItemCount(): Int = tickerList.size


    override fun onBindViewHolder(holder: ItemTickerViewHolder, position: Int) =
        holder.bind(tickerList[position])

    fun setData(list: List<TickerModel>) {
        tickerList.run {
            clear()
            addAll(list)
        }
    }

    inner class ItemTickerViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

        private val tvCoinName by lazy { view.item_ticker_tv_coin_name }
        private val tvNowPrice by lazy { view.item_ticker_tv_now_price }
        private val tvCompareBefore by lazy { view.item_ticker_tv_compare_before }
        private val tvTotalDealPrice by lazy { view.item_ticker_tv_total_deal_price }

        fun bind(tickerModel: TickerModel) {

            tvCoinName.text = tickerModel.coinName

            tvNowPrice.text = tickerModel.nowPrice

            when {
                tickerModel.prevClosingPrice > tickerModel.tradePrice -> {
                    tvCompareBefore.setTextColor(view.context.getColor(R.color.blue))
                }
                tickerModel.prevClosingPrice < tickerModel.tradePrice -> {
                    tvCompareBefore.setTextColor(view.context.getColor(R.color.red))
                }
                else -> {
                    tvCompareBefore.setTextColor(view.context.getColor(R.color.black))
                }
            }
            tvCompareBefore.text = tickerModel.compareBeforePercentage

            tvTotalDealPrice.text = tickerModel.totalDealPrice

        }

    }

}
