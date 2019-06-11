package com.nanamare.mac.sample.adapter.viewholder

import android.view.View
import com.nanamare.mac.sample.api.upbit.TickerModel
import kotlinx.android.synthetic.main.item_ticker.view.*

class TickerViewHolder(itemView: View) : BaseViewHolder<TickerModel>(itemView) {

    override fun bind(data: Any) {

        val data = data as TickerModel

        itemView.tv_coin_name.text = data.market!!.split('-')[1]
        itemView.tv_coin_current_price.text = data.tradePrice.toString()
        itemView.tv_coin_compare_rate.text = when {
            data.signedChangeRate!! < 0 -> String.format("%.2f%%", data.signedChangeRate)
            else -> String.format("%s%.2f%%", '+', data.signedChangeRate)
        }
        itemView.tv_coin_all_price.text = String.format("%.2f M", data.accTradePrice24h)

    }

}