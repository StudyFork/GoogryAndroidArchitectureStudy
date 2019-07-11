package com.nanamare.mac.sample.adapter.viewholder

import android.view.ViewGroup
import com.nanamare.mac.sample.R
import com.nanamare.mac.sample.api.upbit.CoinModel
import com.nanamare.mac.sample.base.BaseViewHolder
import com.nanamare.mac.sample.databinding.ItemTickerBinding
import kotlinx.android.synthetic.main.item_ticker.view.*

class TickerViewHolder(parent: ViewGroup) : BaseViewHolder<CoinModel, ItemTickerBinding>(R.layout.item_ticker, parent) {
    override fun bind(data: CoinModel) {
        binding.run {
            tvCoinAllPrice.text = data.accTradePrice24h.toString()
            tvCoinCompareRate.text = data.signedChangeRate.toString()
            tvCoinName.text = data.market!!.split('-')[1]
            tvCoinCurrentPrice.text = data.tradePrice.toString()
        }
    }
}