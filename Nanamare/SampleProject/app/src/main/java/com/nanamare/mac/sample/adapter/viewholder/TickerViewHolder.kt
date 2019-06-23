package com.nanamare.mac.sample.adapter.viewholder

import android.view.ViewGroup
import androidx.annotation.LayoutRes
import com.nanamare.mac.sample.api.upbit.TickerModel
import com.nanamare.mac.sample.base.BaseViewHolder
import kotlinx.android.synthetic.main.item_ticker.view.*

class TickerViewHolder(
    @LayoutRes layoutRes: Int,
    parent: ViewGroup
) : BaseViewHolder<TickerModel>(layoutRes, parent) {
    override fun bind(data: TickerModel) {
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