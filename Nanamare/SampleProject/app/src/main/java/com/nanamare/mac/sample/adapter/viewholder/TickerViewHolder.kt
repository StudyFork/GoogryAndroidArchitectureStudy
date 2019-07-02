package com.nanamare.mac.sample.adapter.viewholder

import android.view.ViewGroup
import com.nanamare.mac.sample.R
import com.nanamare.mac.sample.api.upbit.CoinModel
import com.nanamare.mac.sample.base.BaseViewHolder
import kotlinx.android.synthetic.main.item_ticker.view.*

class TickerViewHolder(parent: ViewGroup) : BaseViewHolder<CoinModel>(R.layout.item_ticker, parent) {
    override fun bind(data: CoinModel) {
        /**
         * Todo market 에 맞춰서 나중에 포맷 바꿔야함
         */
        data.run {
            with(itemView) {
                tv_coin_name.text = data.market!!.split('-')[1]
                tv_coin_current_price.text = data.tradePrice.toString()
                tv_coin_compare_rate.text = data.signedChangeRate.toString()
                tv_coin_all_price.text = data.accTradePrice24h.toString()
            }
        }

    }

}