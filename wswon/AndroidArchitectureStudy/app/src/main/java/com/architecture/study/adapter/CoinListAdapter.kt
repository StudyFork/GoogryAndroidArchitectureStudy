package com.architecture.study.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.architecture.study.R
import com.architecture.study.adapter.viewholder.CoinViewHolder
import com.architecture.study.model.Ticker
import java.text.DecimalFormat

class CoinListAdapter(internal var context: Context) : RecyclerView.Adapter<CoinViewHolder>() {

    private var mCoinListData: List<Ticker> = arrayListOf()
    private var mCoinItemClickListner: CoinItemRecyclerViewClickListener? = null
    private var monetaryUnitName = ""

    interface CoinItemRecyclerViewClickListener {
        fun onItemClicked(position: Int)
        fun onShareButtonClicked(position: Int)
    }

    fun setOnClickListener(listener: CoinItemRecyclerViewClickListener) {
        this.mCoinItemClickListner = listener
    }

    fun setData(coinListData: List<Ticker>) {
        this.mCoinListData = coinListData
    }

    /* KRW, BTC, ETH, USDT 코인 이름 식별자 */
    fun setMonetaryUnitName(monetaryUnitName: String) {
        this.monetaryUnitName = monetaryUnitName
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_coin, parent, false)

        val height = parent.measuredHeight / 10
        val width = parent.measuredWidth
        view.layoutParams = RecyclerView.LayoutParams(width, height)

        return CoinViewHolder(view)
    }

    override fun getItemCount(): Int = mCoinListData.size

    override fun onBindViewHolder(holder: CoinViewHolder, position: Int) {
        val pos = holder.adapterPosition

        val coinTickerData = mCoinListData[pos]

        holder.run {
            itemView.setOnClickListener {
                mCoinItemClickListner?.let {
                    it.onItemClicked(pos)
                }
            }

            tvCoinName.text = coinTickerData.market.replace("$monetaryUnitName-", "")
            tvNowPrice.text = DecimalFormat("0.########").format(coinTickerData.trade_price)
            if (coinTickerData.trade_price > coinTickerData.prev_closing_price) {
                tvCompareYesterday.text =
                    DecimalFormat("0.##").format((1 - (coinTickerData.prev_closing_price / coinTickerData.trade_price)) * 10.0) + "%"
                tvCompareYesterday.setTextColor(ContextCompat.getColor(context, R.color.colorRed))
            } else {
                tvCompareYesterday.text =
                    "-" + DecimalFormat("0.##").format((1 - (coinTickerData.trade_price / coinTickerData.prev_closing_price)) * 10.0) + "%"
                tvCompareYesterday.setTextColor(ContextCompat.getColor(context, R.color.colorBlue))
            }

            /* 거래대금 단위 예외처리 */
            tvTransactionAmount.text = when (monetaryUnitName) {
                context.getString(R.string.monetary_unit_1) -> {
                    String.format("%,d", (coinTickerData.acc_trade_price_24h / 1000000).toInt()) + "M"
                }
                context.getString(R.string.monetary_unit_2) -> {
                    String.format("%,d", DecimalFormat("0.###").format(coinTickerData.acc_trade_price_24h).split(".")[0].toInt()) +
                            "." + DecimalFormat("0.###").format(coinTickerData.acc_trade_price_24h).split(".")[1]
                }
                context.getString(R.string.monetary_unit_3) -> {
                    String.format("%,d", DecimalFormat("0.###").format(coinTickerData.acc_trade_price_24h).split(".")[0].toInt()) +
                            "." + DecimalFormat("0.###").format(coinTickerData.acc_trade_price_24h).split(".")[1]
                }
                else -> {
                    String.format("%,d", DecimalFormat("0.###").format(coinTickerData.acc_trade_price_24h / 1000).split(".")[0].toInt()) + " k"
                }
            }
        }
    }
}