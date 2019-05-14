package dev.daeyeon.gaasproject.ui.ticker.adapter

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import dev.daeyeon.gaasproject.R
import dev.daeyeon.gaasproject.data.source.Ticker
import dev.daeyeon.gaasproject.databinding.ItemTickerBinding

class TickerAdapter : RecyclerView.Adapter<TickerAdapter.TickerViewHolder>(),
    TickerAdapterContract.View,
    TickerAdapterContract.Model {

    private val tickerList = mutableListOf<Ticker>()

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): TickerViewHolder {
        val binding = DataBindingUtil.inflate<ItemTickerBinding>(
            LayoutInflater.from(viewGroup.context),
            R.layout.item_ticker,
            viewGroup,
            false
        )
        return TickerViewHolder(binding)
    }

    override fun getItemCount(): Int = tickerList.size

    override fun onBindViewHolder(holder: TickerViewHolder, position: Int) {
        holder.binding.ticker = tickerList[position]
    }

    override fun notifyAdapter() {
        notifyDataSetChanged()
    }

    /**
     * 리스트 추가
     */
    override fun addList(list: List<Ticker>) {
        tickerList.addAll(list)
    }

    /**
     * 비우기
     */
    override fun clearList() {
        tickerList.clear()
    }

    /**
     * 초기화 후 리스트 추가
     */
    override fun replaceList(list: List<Ticker>) {
        clearList()
        addList(list)
    }

    class TickerViewHolder(val binding: ItemTickerBinding) : RecyclerView.ViewHolder(binding.root)
}
