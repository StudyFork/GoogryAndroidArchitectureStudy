package my.gong.studygong.mvp.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.item_ticker.view.*
import my.gong.studygong.R
import my.gong.studygong.data.model.Ticker

class CoinAdapter
    : RecyclerView.Adapter<CoinAdapter.ViewHolder>() {

    private val coinList: MutableList<Ticker> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_ticker, parent, false))

    override fun getItemCount() = coinList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder) {
            coinList[position].let {
                coinMarket.text = it.market
                coinOpningPrice.text = it.tradePrice
                coinChangePrice.text = it.changeRate
            }
        }
    }

    fun refreshData(coinList: List<Ticker>) {
        this.coinList.run {
            clear()
            addAll(coinList)
        }
        notifyDataSetChanged()
    }


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val coinMarket = view.txt_item_ticker_market!!
        val coinOpningPrice = view.txt_item_ticker_opening_price
        val coinChangePrice = view.txt_item_ticker_change_price
        val coinChangeAccTradePrice = view.txt_item_ticker_acc_trade_price
    }
}