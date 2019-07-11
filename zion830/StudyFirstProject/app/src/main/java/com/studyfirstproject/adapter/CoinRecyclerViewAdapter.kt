package com.studyfirstproject.adapter

import android.databinding.DataBindingUtil
import android.support.annotation.LayoutRes
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.studyfirstproject.base.BaseViewHolder
import com.studyfirstproject.data.model.TickerModel
import com.studyfirstproject.databinding.ItemCoinInfoBinding


class CoinRecyclerViewAdapter(
    @LayoutRes private val layoutResId: Int
) : RecyclerView.Adapter<CoinRecyclerViewAdapter.CoinItemViewHolder>() {
    private val coinList = mutableListOf<TickerModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: ItemCoinInfoBinding =
            DataBindingUtil.inflate(inflater, layoutResId, parent, false)

        return CoinItemViewHolder(binding)
    }

    override fun getItemCount(): Int = coinList.size

    override fun onBindViewHolder(parent: CoinItemViewHolder, position: Int) {
        parent.bind(coinList[position])
    }

    fun getItem(position: Int) = coinList[position]

    fun setCoinList(data: List<TickerModel>) {
        coinList.clear()
        coinList.addAll(data)
        notifyDataSetChanged()
    }

    inner class CoinItemViewHolder(private val binding: ItemCoinInfoBinding) :
        BaseViewHolder<TickerModel>(binding.root) {

        override fun bind(item: TickerModel) {
            binding.item = item
        }
    }
}
