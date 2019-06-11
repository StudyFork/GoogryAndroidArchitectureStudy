package com.nanamare.mac.sample.adapter.viewholder

import android.view.View
import com.nanamare.mac.sample.api.upbit.TickerModel
import kotlinx.android.synthetic.main.item_ticker.view.*

class TickerViewHolder(itemView: View) : BaseViewHolder<TickerModel>(itemView) {

    override fun bind(data: Any) {

        val data = data as TickerModel

        /**
         * Todo market 에 맞춰서 나중에 포맷 바꿔야함
         */
        itemView.tv_coin_name.text = data.market!!.split('-')[1]
        itemView.tv_coin_current_price.text = data.tradePrice.toString()
        itemView.tv_coin_compare_rate.text = data.signedChangeRate.toString()
        itemView.tv_coin_all_price.text = data.accTradePrice24h.toString()

    }

}