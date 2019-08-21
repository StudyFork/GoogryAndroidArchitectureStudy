package com.test.androidarchitecture.adpter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.test.androidarchitecture.R
import com.test.androidarchitecture.data.Coin
import com.test.androidarchitecture.data.CoinFormat
import com.test.androidarchitecture.ext.*
import kotlinx.android.synthetic.main.item_coin.view.*
import kotlin.collections.ArrayList

class CoinAdapter : RecyclerView.Adapter<CoinAdapter.CoinViewHolder>() {

    private val coinFormatList: MutableList<CoinFormat> = ArrayList()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinViewHolder {
        return CoinViewHolder(parent)
    }

    override fun getItemCount(): Int {
        return coinFormatList.size
    }

    override fun onBindViewHolder(holder: CoinViewHolder, position: Int) {
        holder.onBindView(coinFormatList[position])
    }


    fun addItem(coinList: List<CoinFormat>) {
        this.coinFormatList.addAll(coinList)
        notifyDataSetChanged()
    }


    class CoinViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_coin, parent, false)
    ) {

        private val coinNameTextView = itemView.item_coin_name_text

        private val coinNowPriceTextView = itemView.item_coin_now_price_text

        private val coinPreParePercentTextView = itemView.item_coin_prepare_percent_text

        private val coinDealPriceTextView = itemView.item_coin_deal_price_text

        fun onBindView(coinFormat: CoinFormat) {
            coinNameTextView.text = coinFormat.marketName
            coinNowPriceTextView.text = coinFormat.tradePrice
            coinPreParePercentTextView.text = coinFormat.changeRate
            coinPreParePercentTextView.setTextColorRes(coinFormat.changeColor)
            coinDealPriceTextView.text = coinFormat.accTradePrice
        }
    }


}