package com.studyfirstproject.adapter

import android.graphics.Color
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.studyfirstproject.R
import com.studyfirstproject.data.model.TickerModel
import com.studyfirstproject.util.NumberFormatUtil
import kotlinx.android.synthetic.main.item_coin_info.view.*
import org.jetbrains.anko.textColor

class CoinRecyclerViewAdapter
    : RecyclerView.Adapter<CoinRecyclerViewAdapter.CoinItemViewHolder>() {
    private var coinList: MutableList<TickerModel> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, type: Int): CoinItemViewHolder {
        val viewGroup = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_coin_info, parent, false)

        return CoinItemViewHolder(viewGroup)
    }

    override fun getItemCount(): Int = coinList.size

    override fun onBindViewHolder(parent: CoinItemViewHolder, position: Int) {
        parent.bind(coinList[position])
    }

    fun getItem(position: Int) = coinList[position]

    fun setCoinList(data: List<TickerModel>) {
        coinList.clear()
        coinList.addAll(data)
        notifyDataSetChanged()
    }

    inner class CoinItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val tvCoinName by lazy { itemView.tv_coin_name as TextView }
        private val tvCoinTradePrice by lazy { itemView.tv_coin_trade_price as TextView }
        private val tvCoinChangeRate by lazy { itemView.tv_coin_change_rate as TextView }
        private val tvCoinAccTradePrice by lazy { itemView.tv_coin_acc_trade_price as TextView }

        fun bind(data: TickerModel) {
            tvCoinName.text = data.market.substring(data.market.indexOf("-") + 1)
            tvCoinTradePrice.text = NumberFormatUtil.insertComma(data.tradePrice.toLong())
            tvCoinChangeRate.text = NumberFormatUtil.getPercent(data.signedChangeRate)
            tvCoinAccTradePrice.text = NumberFormatUtil.skipUnderMillions(data.accTradePrice24h)

            tvCoinChangeRate.textColor = when {
                data.signedChangeRate > 0 -> Color.BLUE
                data.signedChangeRate < 0 -> Color.RED
                else -> Color.BLACK
            }
        }
    }
}
