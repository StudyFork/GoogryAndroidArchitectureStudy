package com.architecture.study.view.coin.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.architecture.study.R
import com.architecture.study.view.coin.adapter.holder.CoinViewHolder
import com.architecture.study.data.model.Ticker

class CoinListAdapter(private val context: Context, private val listener: CoinItemRecyclerViewClickListener) :
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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_coin, parent, false)

        val height = parent.measuredHeight / 10
        val width = parent.measuredWidth
        view.layoutParams = RecyclerView.LayoutParams(width, height)

        return CoinViewHolder(view)
    }

    override fun getItemCount(): Int = coinListData.size

    override fun onBindViewHolder(holder: CoinViewHolder, position: Int) =
        holder.bind(context, coinListData[position], listener)

}