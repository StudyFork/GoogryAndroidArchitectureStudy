package me.hoyuo.myapplication.ui.adapter

import android.graphics.Color
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import kotlinx.android.synthetic.main.coin_item.view.*
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
                coinTradePrice.text = ticker.opening_price.toString()

                if (ticker.opening_price / ticker.prev_closing_price > 0) {
                    coinPrevClosePriceRatio.setTextColor(Color.RED)
                } else {
                    coinPrevClosePriceRatio.setTextColor(Color.BLUE)
                }

                coinPrevClosePriceRatio.text = ((1 - (ticker.prev_closing_price / ticker.opening_price)) * 100).toInt()
                    .toString()

                coinTradeVolume.text = ticker.trade_volume.toString()
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