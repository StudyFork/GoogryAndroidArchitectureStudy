package my.gong.studygong.viewcontrol.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.item_ticker.view.*
import my.gong.studygong.R
import my.gong.studygong.data.model.Ticker

class CoinAdapter(
    val context: Context,
    val coinList: MutableList<Ticker>
    )
    : RecyclerView.Adapter<CoinAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
         ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_ticker, parent ,false))

    override fun getItemCount() = coinList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.coinMarket.text = coinList[position].market
    }

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val coinMarket = view.txt_item_ticker_market
    }
}