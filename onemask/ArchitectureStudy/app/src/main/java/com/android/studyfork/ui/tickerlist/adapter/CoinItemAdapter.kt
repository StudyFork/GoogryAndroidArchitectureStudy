package com.android.studyfork.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.android.studyfork.R
import com.android.studyfork.databinding.ItemCoinBinding
import com.android.studyfork.network.model.Ticker

class CoinItemAdapter : RecyclerView.Adapter<CoinItemAdapter.ViewHolder>() {

    private val dataList = mutableListOf<Ticker>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : ViewHolder {
        return ViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_coin,
                parent,
                false

            )
        )
    }

    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(dataList[position])

    fun setData(dataSet: List<Ticker>) {
        with(this.dataList) {
            clear()
            addAll(dataSet)
        }
        notifyDataSetChanged()
    }

    class ViewHolder(private val binding: ItemCoinBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(ticker: Ticker) {
            binding.run {
                tickerItem = ticker
                executePendingBindings()
            }
        }

    }
}