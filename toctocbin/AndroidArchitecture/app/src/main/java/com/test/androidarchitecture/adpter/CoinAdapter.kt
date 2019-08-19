package com.test.androidarchitecture.adpter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.test.androidarchitecture.R
import com.test.androidarchitecture.data.Coin
import kotlinx.android.synthetic.main.item_coin.view.*
import java.text.DecimalFormat
import java.text.NumberFormat
import java.util.*
import kotlin.collections.ArrayList

class CoinAdapter(private val coinType: String) : RecyclerView.Adapter<CoinAdapter.CoinViewHolder>() {

    private val coinList: MutableList<Coin> = ArrayList()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinViewHolder {
        return CoinViewHolder(parent)
    }

    override fun getItemCount(): Int {
        return coinList.size
    }

    override fun onBindViewHolder(holder: CoinViewHolder, position: Int) {
        holder.onBindView(coinList[position], coinType)
    }


    fun addItem(coinList: List<Coin>) {
        this.coinList.addAll(coinList)
    }


    class CoinViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_coin, parent, false)
    ) {

        val coinNameTextView = itemView.item_coin_name_text

        val coinNowPriceView = itemView.item_coin_now_price_text

        val coinPreParePercentTextView = itemView.item_coin_prepare_percent_text

        val coinDealPriceTextView = itemView.item_coin_deal_price_text

        fun onBindView(coin: Coin, coinType: String) {
            coinNameTextView.text = coin.market.replace(coinType, "").replace("-", "")
            coinNowPriceView.text = formatPrice(coin.trade_price)
            setPrePercent(coinPreParePercentTextView, coin.signed_change_rate, itemView.context)
            coinDealPriceTextView.text = formatDealPrice(coin.acc_trade_price_24h)
        }

        private fun formatPrice(price: Double): String {
            return when {
                price < 1 -> String.format("%.8f", price)
                else -> NumberFormat.getNumberInstance(Locale.US).format(price)
            }
        }

        private fun setPrePercent(textview: TextView, signedChangeRate: Double, context: Context) {
            val color: Int = when {
                signedChangeRate > 0 -> ContextCompat.getColor(context, R.color.colorRed)
                signedChangeRate < 0 -> ContextCompat.getColor(context, R.color.colorBlue)
                else -> ContextCompat.getColor(context, R.color.colorBlack)
            }
            textview.setTextColor(color)
            val rateStr = String.format("%.2f",signedChangeRate*100)
            textview.text = "$rateStr%"
        }

        private fun formatDealPrice(price: Double): String {
            val df = DecimalFormat("#,###")
            return if (price > 1000000) {
                df.format(price/1000000)+"M"
            } else {
                df.format(price)
            }
        }

    }


}