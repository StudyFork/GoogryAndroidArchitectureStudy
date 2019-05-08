package me.hoyuo.myapplication.ui.coinlist.adapter

import android.graphics.Color
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import kotlinx.android.synthetic.main.coin_item.view.coinName
import kotlinx.android.synthetic.main.coin_item.view.coinPrevClosePriceRatio
import kotlinx.android.synthetic.main.coin_item.view.coinTradePrice
import kotlinx.android.synthetic.main.coin_item.view.coinTradeVolume
import me.hoyuo.myapplication.model.upbit.Ticker
import me.hoyuo.myapplication.ui.coinlist.adapter.viewholder.CoinItemViewHolder
import java.util.concurrent.CopyOnWriteArrayList

class ItemAdapter : RecyclerView.Adapter<CoinItemViewHolder>() {
    private val itemList = CopyOnWriteArrayList<Ticker>()
    private lateinit var listener: (ticker: Ticker) -> Unit

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CoinItemViewHolder = CoinItemViewHolder(parent)

    override fun onBindViewHolder(holder: CoinItemViewHolder, position: Int) {
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
                setOnClickListener { listener(ticker) }
            }
        }
    }

    override fun getItemCount(): Int = itemList.size

    fun loadData(tickerList: List<Ticker>) {
        itemList.clear()
        itemList.addAll(tickerList)
        notifyDataSetChanged()
    }

    fun setItemClickListener(listener: (ticker: Ticker) -> Unit) {
        this.listener = listener
    }
}