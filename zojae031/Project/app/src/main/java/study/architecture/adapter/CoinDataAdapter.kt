package study.architecture.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import study.architecture.R
import study.architecture.vo.Ticker

class CoinDataAdapter(private val lists: List<Ticker>) : RecyclerView.Adapter<CoinDataAdapter.Holder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item, null)
        return Holder(view)
    }

    override fun getItemCount(): Int = lists.size


    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.name.text = lists[position].toString()
        holder.price.text = lists[position].toString()
        holder.last.text = lists[position].toString()
        holder.money.text = lists[position].toString()
    }

    inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name: TextView = itemView.findViewById(R.id.name)
        val price: TextView = itemView.findViewById(R.id.price)
        val last: TextView = itemView.findViewById(R.id.last)
        val money: TextView = itemView.findViewById(R.id.money)

    }
}