package sample.nackun.com.studyfirst

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.ticker_item.view.*
import sample.nackun.com.studyfirst.market.Ticker

class TickerAdapter(val items: ArrayList<Ticker>, val context: Context): RecyclerView.Adapter<TickerViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): TickerViewHolder {
        return TickerViewHolder(LayoutInflater.from(context).inflate(R.layout.ticker_item, parent, false))
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(tickerViewHolder: TickerViewHolder, p1: Int) {
        tickerViewHolder?.tickerName?.text = items.get(p1).market
        tickerViewHolder?.currentPrice?.text = String.format("%,d", items.get(p1).trade_price.toInt())
        tickerViewHolder?.comparePrice?.text = (items.get(p1).signed_change_price / 100000).toString() + "%"
        //tickerViewHolder?.changePrice?.text = String.format("%,d", items.get(p1).acc_trade_price_24h.toInt()) + " M"
        tickerViewHolder?.changePrice?.text = String.format("%,d", items.get(p1).acc_trade_price_24h.toString().substring(0, 6).replace(".","").toInt())+ " M"
    }
}

class TickerViewHolder (view: View) : RecyclerView.ViewHolder(view) {
    // Holds the TextView that will add each animal to
    val tickerName = view.tickerName
    val currentPrice = view.currentPrice
    val comparePrice = view.comparePrice
    val changePrice = view.changePrice
}