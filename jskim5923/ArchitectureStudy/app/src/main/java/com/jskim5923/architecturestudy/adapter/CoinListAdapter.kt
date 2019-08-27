package com.jskim5923.architecturestudy.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.jskim5923.architecturestudy.R
import com.jskim5923.architecturestudy.model.Ticker

class CoinListAdapter :
    RecyclerView.Adapter<CoinListAdapter.CoinListViewHolder>() {

    private var itemList = mutableListOf<Ticker>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinListViewHolder {
        return CoinListViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_coin_list,
                parent,
                false
            )
        )
    }

    override fun getItemCount() = itemList.size

    override fun onBindViewHolder(holder: CoinListViewHolder, position: Int) {
        holder.bind(itemList[position])
    }

    fun updateItem(itemList: List<Ticker>) {
        this.itemList.clear()
        this.itemList.addAll(itemList)
        notifyDataSetChanged()

    }

    class CoinListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val coinName: TextView = itemView.findViewById(R.id.tv_coinName)

        private val currentPrice: TextView = itemView.findViewById(R.id.current_price)

        private val diff: TextView = itemView.findViewById(R.id.tv_diff)

        private val tradeVolume: TextView = itemView.findViewById(R.id.tv_tradeVolume)

        fun bind(item: Ticker) {
            coinName.text = item.name
            currentPrice.text = item.currentPrice
            diff.run {
                text = item.diff
                setTextColor(ContextCompat.getColor(context, item.diffTextColorId))
            }
            tradeVolume.text = item.tradeVolume
        }

    }
}

