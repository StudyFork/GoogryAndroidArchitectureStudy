package me.hoyuo.myapplication.ui.adapter

import android.graphics.Color
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import kotlinx.android.synthetic.main.coin_item.view.coinName
import kotlinx.android.synthetic.main.coin_item.view.coinPrevClosePriceRatio
import kotlinx.android.synthetic.main.coin_item.view.coinTradePrice
import kotlinx.android.synthetic.main.coin_item.view.coinTradeVolume
import me.hoyuo.myapplication.model.upbit.Ticker
import me.hoyuo.myapplication.ui.adapter.viewholder.CoinItem
import java.util.concurrent.CopyOnWriteArrayList

class ItemAdapter : RecyclerView.Adapter<CoinItem>() {
    private var itemList = CopyOnWriteArrayList<Ticker>()
    private var listener: ItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinItem = CoinItem(parent)

    override fun onBindViewHolder(holder: CoinItem, position: Int) {
        itemList[position].let { ticker ->
            with(holder.containerView) {
                coinName.text = ticker.market
                coinTradePrice.text = ticker.openingPrice.toString()

                if (ticker.openingPrice / ticker.prevClosingPrice > 0) {
                    coinPrevClosePriceRatio.setTextColor(Color.RED)
                } else {
                    coinPrevClosePriceRatio.setTextColor(Color.BLUE)
                }

                coinPrevClosePriceRatio.text = ((1 - (ticker.prevClosingPrice / ticker.openingPrice)) * 100).toInt()
                        .toString()

                coinTradeVolume.text = ticker.tradeVolume.toString()
                setOnClickListener { listener?.onItemClick(ticker) }
            }
        }
    }

    override fun getItemCount(): Int = itemList.size

    fun loadData(tickerList: List<Ticker>) {
        itemList.clear()
        itemList.addAll(tickerList)
        notifyDataSetChanged()
    }

    fun setItemClickListener(listener: ItemClickListener) {
        this.listener = listener
    }

    interface ItemClickListener {
        fun onItemClick(ticker: Ticker)
    }
}