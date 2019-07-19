package com.android.studyfork.ui.adpater

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.studyfork.R
import com.android.studyfork.ext.inflate
import com.android.studyfork.repository.model.TickerResponse
import kotlinx.android.synthetic.main.item_coin.view.*

class CoinItemAdapter()
    : RecyclerView.Adapter<CoinItemAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(parent.inflate(R.layout.item_coin))

    private var dataSet : List<TickerResponse> = ArrayList()

    override fun getItemCount(): Int = dataSet.size

    override fun onBindViewHolder(holder: CoinItemAdapter.ViewHolder, position: Int) =
        holder.bind(position,dataSet[position])

    fun prependData(dataSet : List<TickerResponse>?){
        this.dataSet = emptyList()
        dataSet?.let {
            this.dataSet = ArrayList(dataSet)
        }
        notifyDataSetChanged()
    }

    fun clearData(){
        dataSet = emptyList()
        notifyDataSetChanged()
    }

    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        fun bind(position : Int,ticker : TickerResponse) = with(itemView){
            text_title.text = ticker.market!!.split("-")[1]
            text_current_price.text = ticker.trade_price.toString()
            text_before_day.text = ticker.signed_change_rate.toString()
            text_total_trade.text  = ticker.acc_trade_price_24h.toString()
        }
    }
}