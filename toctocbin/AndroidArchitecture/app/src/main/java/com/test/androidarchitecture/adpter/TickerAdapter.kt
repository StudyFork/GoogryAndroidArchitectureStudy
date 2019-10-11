package com.test.androidarchitecture.adpter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.test.androidarchitecture.R
import com.test.androidarchitecture.data.TickerFormat
import com.test.androidarchitecture.databinding.ItemCoinBinding
import com.test.androidarchitecture.ext.setTextColorRes

class TickerAdapter : RecyclerView.Adapter<TickerAdapter.CoinViewHolder>() {

    private val tickerFormatList = mutableListOf<TickerFormat>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinViewHolder {
        val binding = DataBindingUtil.inflate<ItemCoinBinding>(LayoutInflater.from(parent.context), R.layout.item_coin, parent, false);
        return CoinViewHolder(binding)
    }

    override fun getItemCount() = tickerFormatList.size

    override fun onBindViewHolder(holder: CoinViewHolder, position: Int) {
        holder.onBindView(tickerFormatList[position])
        holder.binding.executePendingBindings()
    }

    fun setItem(tickerList: List<TickerFormat>) {
        with(this.tickerFormatList) {
            clear()
            addAll(tickerList)
        }
        notifyDataSetChanged()
    }

    class CoinViewHolder(val binding: ItemCoinBinding) : RecyclerView.ViewHolder(
        binding.root
    ) {

        fun onBindView(tickerFormat: TickerFormat) {
            binding.run {
                itemCoinNameText.text= tickerFormat.marketName
                itemCoinNowPriceText.text = tickerFormat.tradePrice
                itemCoinPreparePercentText.text = tickerFormat.changeRate
                itemCoinPreparePercentText.setTextColorRes(tickerFormat.changeColor)
                itemCoinDealPriceText.text = tickerFormat.accTradePrice
            }
        }
    }
}