package com.test.androidarchitecture.adpter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.test.androidarchitecture.R
import com.test.androidarchitecture.data.Coin
import kotlinx.android.synthetic.main.item_coin.view.*
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
        coinList[position].let { coin ->
            with(holder) {
                coinNameTextView.text = coin.market.replace(coinType, "").replace("-", "")
                coinNowPriceView.text = formatPrice(coin.trade_price)
            }
        }
    }


    fun addItem(coinList: ArrayList<Coin>) {
        this.coinList.addAll(coinList)
    }


    class CoinViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_coin, parent, false)) {

        val coinNameTextView = itemView.item_coin_name_text

        val coinNowPriceView = itemView.item_coin_now_price_text

        val coinPreParePercentTextView = itemView.item_coin_prepare_percent_text

        val coinDealPriceTextView = itemView.item_coin_deal_price_text

    }

    private fun formatPrice(price: Double): String {
        return NumberFormat.getNumberInstance(Locale.US).format(price)
    }

}