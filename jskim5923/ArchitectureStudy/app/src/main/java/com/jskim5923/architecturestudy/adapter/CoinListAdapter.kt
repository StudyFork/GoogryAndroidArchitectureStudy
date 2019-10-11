package com.jskim5923.architecturestudy.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.jskim5923.architecturestudy.R
import com.jskim5923.architecturestudy.databinding.ItemCoinListBinding
import com.jskim5923.architecturestudy.model.Ticker

class CoinListAdapter :
    RecyclerView.Adapter<CoinListAdapter.CoinListViewHolder>() {

    private var itemList = mutableListOf<Ticker>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinListViewHolder {
        return CoinListViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
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

    class CoinListViewHolder(private val binding: ItemCoinListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Ticker) {
            binding.run {
                ticker = item
                executePendingBindings()
            }
        }
    }
}

