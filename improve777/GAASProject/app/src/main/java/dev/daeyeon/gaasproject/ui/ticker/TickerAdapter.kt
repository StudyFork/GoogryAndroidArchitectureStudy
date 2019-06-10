package dev.daeyeon.gaasproject.ui.ticker

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import dev.daeyeon.gaasproject.R
import dev.daeyeon.gaasproject.data.Ticker
import dev.daeyeon.gaasproject.databinding.ItemTickerBinding

class TickerAdapter : RecyclerView.Adapter<TickerAdapter.TickerViewHolder>() {

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
        holder.binding.run {
            ticker = tickerList[position]
            executePendingBindings()
        }
    }

    /**
     * 리스트 추가
     */
    private fun addList(list: List<Ticker>) {
        tickerList.addAll(list)
        notifyDataSetChanged()
    }

    /**
     * clear() 후 리스트 추가
     */
    fun replaceList(list: List<Ticker>) {
        tickerList.clear()
        addList(list)
    }

    class TickerViewHolder(val binding: ItemTickerBinding) : RecyclerView.ViewHolder(binding.root)
}
