package com.architecture.study.view.coin.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.architecture.study.data.model.Ticker
import com.architecture.study.view.coin.adapter.holder.CoinViewHolder

class CoinListAdapter(private val listener: CoinItemRecyclerViewClickListener) :
    RecyclerView.Adapter<CoinViewHolder>() {

    private var coinListData = mutableListOf<Ticker>()

    interface CoinItemRecyclerViewClickListener {
        fun onItemClicked(position: Int)
    }

    fun setData(coinListData: List<Ticker>) {
        this.coinListData.clear()
        this.coinListData.addAll(coinListData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinViewHolder = CoinViewHolder(parent)

    override fun getItemCount(): Int = coinListData.size

    override fun onBindViewHolder(holder: CoinViewHolder, position: Int) = holder.bind(coinListData[position], listener)

}