package study.architecture.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import study.architecture.R
import study.architecture.vo.Ticker

class CoinDataAdapter : RecyclerView.Adapter<CoinDataAdapter.Holder>() {

    var lists: List<Ticker> = listOf()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item, null)
        return Holder(view)
    }

    override fun getItemCount(): Int = lists.size


    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.name.text = lists[position].market
        holder.price.text = lists[position].trade_price.toString()
        holder.last.text = lists[position].change_rate.toString()
        holder.money.text = lists[position].acc_trade_price_24h.toString()
    }

    inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name: TextView = itemView.findViewById(R.id.name)
        val price: TextView = itemView.findViewById(R.id.price)
        val last: TextView = itemView.findViewById(R.id.last)
        val money: TextView = itemView.findViewById(R.id.money)

    }
}