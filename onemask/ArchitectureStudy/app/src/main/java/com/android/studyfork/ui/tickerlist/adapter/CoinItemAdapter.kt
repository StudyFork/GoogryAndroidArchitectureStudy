package com.android.studyfork.ui.adapter

import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.android.studyfork.R
import com.android.studyfork.network.model.Ticker
import com.android.studyfork.util.filterTrade
import com.android.studyfork.util.inflate
import com.android.studyfork.util.setTradeAmount
import com.android.studyfork.util.setTradeDiff

/**
 * created by onemask
 */
class CoinItemAdapter : RecyclerView.Adapter<CoinItemAdapter.ViewHolder>() {

    private val dataList = mutableListOf<Ticker>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(parent.inflate(R.layout.item_coin))

    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(dataList[position])

    fun setData(dataSet: List<Ticker>) {
        with(this.dataList) {
            clear()
            addAll(dataSet)
        }
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val textTitle: TextView = itemView.findViewById(R.id.text_title)
        private val textCurrentPrice: TextView = itemView.findViewById(R.id.text_current_price)
        private val textBeforeDay: TextView = itemView.findViewById(R.id.text_before_day)
        private val textTotalTrade: TextView = itemView.findViewById(R.id.text_total_trade)

        fun bind(ticker: Ticker) = with(itemView) {
            textTitle.text = ticker.marketName.split("-")[1]
            textCurrentPrice.filterTrade(ticker.currentTradePrice)
            textBeforeDay.setTradeDiff(ticker.beforeSignedChangeRate)
            textTotalTrade.setTradeAmount(ticker)
        }
    }
}