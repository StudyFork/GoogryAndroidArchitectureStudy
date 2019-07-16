package study.architecture.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.list_item.view.*
import study.architecture.R
import study.architecture.vo.Ticker

class CoinDataAdapter : RecyclerView.Adapter<CoinDataAdapter.Holder>() {

    var lists: List<Ticker> = listOf()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item, null)
        view.dataWrapper.minimumWidth = view.context.applicationContext.resources.displayMetrics.widthPixels
        return Holder(view)
    }

    override fun getItemCount(): Int = lists.size


    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.name.text = lists[position].market.substringAfter('-')
        val tradePrice = lists[position].trade_price.toInt()
        if (tradePrice > 0) {
            holder.tradePrice.text = String.format("%,d", tradePrice)
        } else {
            holder.tradePrice.text = String.format("%,f", lists[position].trade_price)
        }

        holder.changeRate.text = String.format("%.2f%%", lists[position].change_rate * 100)
        holder.accTradePrice24h.text = String.format("%,d", lists[position].acc_trade_price_24h.toInt())
    }

    inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name: TextView = itemView.findViewById(R.id.name)
        val tradePrice: TextView = itemView.findViewById(R.id.trade_price)
        val changeRate: TextView = itemView.findViewById(R.id.change_rate)
        val accTradePrice24h: TextView = itemView.findViewById(R.id.acc_trade_price)

    }
}