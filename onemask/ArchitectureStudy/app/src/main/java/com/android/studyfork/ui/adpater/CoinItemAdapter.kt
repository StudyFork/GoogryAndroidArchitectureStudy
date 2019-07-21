package com.android.studyfork.ui.adpater

import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.android.studyfork.R
import com.android.studyfork.network.remote.model.TickerResponse
import com.android.studyfork.utill.filterTrade
import com.android.studyfork.utill.inflate
import com.android.studyfork.utill.setTradeAmount
import com.android.studyfork.utill.setTradeDiff

class CoinItemAdapter
    : RecyclerView.Adapter<CoinItemAdapter.ViewHolder>() {

    private var dataSet : List<TickerResponse> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(parent.inflate(R.layout.item_coin))

    override fun getItemCount(): Int = dataSet.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(dataSet[position])

    fun setData(dataSet : List<TickerResponse>?){
        this.dataSet = emptyList()
        dataSet?.let {
            this.dataSet = ArrayList(dataSet)
        }
        notifyDataSetChanged()
    }

    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){

        private val textTitle: TextView = itemView.findViewById(R.id.text_title)
        private val textCurrentPrice: TextView = itemView.findViewById(R.id.text_current_price)
        private val textBeforeDay: TextView = itemView.findViewById(R.id.text_before_day)
        private val textTotalTrade: TextView = itemView.findViewById(R.id.text_total_trade)

        fun bind(ticker: TickerResponse) = with(itemView) {
            textTitle.text = ticker.market.split("-")[1]
            textCurrentPrice.filterTrade(ticker.tradePrice)
            textBeforeDay.setTradeDiff(ticker.signedChangeRate)
            textTotalTrade.setTradeAmount(ticker.accTradePrice24h)
        }
    }
}