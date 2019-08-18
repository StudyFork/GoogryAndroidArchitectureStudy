package com.jake.archstudy.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jake.archstudy.databinding.ItemTickerBinding
import com.jake.archstudy.ext.setChangeRate
import com.jake.archstudy.ext.setCoinName
import com.jake.archstudy.ext.setCurrentPrice
import com.jake.archstudy.ext.setTradePrice
import com.jake.archstudy.network.response.TickerResponse

class TickersAdapter : RecyclerView.Adapter<TickersAdapter.ViewHolder>() {

    private val items = mutableListOf<TickerResponse>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
            ItemTickerBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    fun set(list: List<TickerResponse>) {
        items.clear()
        items.addAll(list)
    }

    inner class ViewHolder(
        private val binding: ItemTickerBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: TickerResponse) {
            binding.run {
                tvName.setCoinName(item.market)
                tvCurrentPrice.setCurrentPrice(item.tradePrice)
                tvChangeRate.setChangeRate(item.change,item.signedChangeRate)
                tvAccTradePrice.setTradePrice(item.accTradePrice24h)
            }
        }

    }
}