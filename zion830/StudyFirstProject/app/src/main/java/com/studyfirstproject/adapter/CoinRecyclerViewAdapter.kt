package com.studyfirstproject.adapter

import android.support.annotation.LayoutRes
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.studyfirstproject.base.BaseViewHolder
import com.studyfirstproject.data.model.TickerModel
import com.studyfirstproject.databinding.ItemCoinInfoBinding


class CoinRecyclerViewAdapter(
    @LayoutRes private val layoutResId: Int,
    private val bindingVariableId: Int
) : RecyclerView.Adapter<BaseViewHolder<ItemCoinInfoBinding, TickerModel>>() {
    private val coinList = mutableListOf<TickerModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        object : BaseViewHolder<ItemCoinInfoBinding, TickerModel>(
            layoutResId, parent, bindingVariableId
        ) {}

    override fun onBindViewHolder(
        parent: BaseViewHolder<ItemCoinInfoBinding, TickerModel>,
        position: Int
    ) {
        parent.bind(coinList[position])
    }

    override fun getItemCount(): Int = coinList.size

    fun getItem(position: Int) = coinList[position]

    fun setCoinList(data: List<TickerModel>) {
        coinList.clear()
        coinList.addAll(data)
        notifyDataSetChanged()
    }
}
