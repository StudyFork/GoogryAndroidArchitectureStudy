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

        private val coinNameTextView = binding.itemCoinNameText
        private val coinNowPriceTextView = binding.itemCoinNowPriceText
        private val coinPreParePercentTextView = binding.itemCoinPreparePercentText
        private val coinDealPriceTextView = binding.itemCoinDealPriceText

        fun onBindView(tickerFormat: TickerFormat) {
            coinNameTextView.text = tickerFormat.marketName
            coinNowPriceTextView.text = tickerFormat.tradePrice
            coinPreParePercentTextView.text = tickerFormat.changeRate
            coinPreParePercentTextView.setTextColorRes(tickerFormat.changeColor)
            coinDealPriceTextView.text = tickerFormat.accTradePrice
        }
    }
}