package com.test.androidarchitecture.adpter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.test.androidarchitecture.R
import com.test.androidarchitecture.data.Coin
import com.test.androidarchitecture.ext.setChangeRate
import com.test.androidarchitecture.ext.setCoinNameText
import com.test.androidarchitecture.ext.setNowPriceText
import com.test.androidarchitecture.ext.setTradePriceText
import kotlinx.android.synthetic.main.item_coin.view.*
import kotlin.collections.ArrayList

class CoinAdapter : RecyclerView.Adapter<CoinAdapter.CoinViewHolder>() {

    private val coinList: MutableList<Coin> = ArrayList()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinViewHolder {
        return CoinViewHolder(parent)
    }

    override fun getItemCount(): Int {
        return coinList.size
    }

    override fun onBindViewHolder(holder: CoinViewHolder, position: Int) {
        holder.onBindView(coinList[position])
    }


    fun addItem(coinList: List<Coin>) {
        this.coinList.addAll(coinList)
        notifyDataSetChanged()
    }


    class CoinViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_coin, parent, false)
    ) {

        private val coinNameTextView = itemView.item_coin_name_text

        private val coinNowPriceTextView = itemView.item_coin_now_price_text

        private val coinPreParePercentTextView = itemView.item_coin_prepare_percent_text

        private val coinDealPriceTextView = itemView.item_coin_deal_price_text

        fun onBindView(coin: Coin) {
            coinNameTextView.setCoinNameText(coin.market)
            coinNowPriceTextView.setNowPriceText(coin.tradePrice)
            coinPreParePercentTextView.setChangeRate(coin.signedChangeRate, coin.change)
            coinDealPriceTextView.setTradePriceText(coin.accTradePrice24h)
        }
    }


}