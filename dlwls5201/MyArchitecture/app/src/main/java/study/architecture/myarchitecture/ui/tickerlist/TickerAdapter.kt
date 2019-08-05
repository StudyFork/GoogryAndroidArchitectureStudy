package study.architecture.myarchitecture.ui.tickerlist

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import study.architecture.myarchitecture.R
import study.architecture.myarchitecture.ui.model.TickerItem
import study.architecture.myarchitecture.util.Filter
import study.architecture.myarchitecture.util.Filter.ASC
import study.architecture.myarchitecture.util.Filter.DESC
import study.architecture.myarchitecture.util.inflate
import study.architecture.myarchitecture.util.setTradeDiffColor

class TickerAdapter : RecyclerView.Adapter<TickerAdapter.TickerViewHolder>() {

    private val tickers = mutableListOf<TickerItem>()

    private var listener: TickerClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TickerViewHolder {
        return TickerViewHolder.newInstance(
            parent,
            listener
        )
    }

    override fun getItemCount() = tickers.size

    override fun onBindViewHolder(holder: TickerViewHolder, position: Int) {
        holder.onBindView(tickers[position])
    }

    fun setTickerClickListener(listener: TickerClickListener) {
        this.listener = listener
    }

    fun setItem(newTickers: MutableList<TickerItem>) {
        tickers.addAll(newTickers)
        notifyDataSetChanged()
    }

    interface TickerClickListener {

        fun onItemClick(ticker: TickerItem)
    }

    /**
     * 정렬
     */
    fun orderByField(bundle: Bundle) {

        val order = bundle.getInt(Filter.KEY_ORDER)

        val field = bundle.getString(Filter.KEY_FIELD)

        //Dlog.d("order : $order , field : $field")
        when (field) {

            Filter.COIN_NAME -> {
                val selector: (TickerItem) -> String = { it.coinName }
                setOrderByField(selector, order)
            }

            Filter.LAST -> {
                val selector: (TickerItem) -> Double = { it.tradePrice }
                setOrderByField(selector, order)
            }

            Filter.TRADE_DIFF -> {
                val selector: (TickerItem) -> Double = { it.signedChangeRate }
                setOrderByField(selector, order)
            }

            Filter.TRADE_AMOUNT -> {
                val selector: (TickerItem) -> Double = { it.accTradePrice24h }
                setOrderByField(selector, order)
            }
        }
    }

    private fun <R : Comparable<R>> setOrderByField(selector: (TickerItem) -> R, order: Int) {
        if (order == ASC) {
            tickers.sortBy(selector)
        } else if (order == DESC) {
            tickers.sortByDescending(selector)
        }

        notifyDataSetChanged()
    }

    /**
     * ViewHolder
     */
    class TickerViewHolder(
        itemView: View,
        private val listener: TickerClickListener?
    ) : RecyclerView.ViewHolder(itemView) {

        private val tvMarket: TextView = itemView.findViewById(R.id.tvMarket)
        private val tvTradePrice: TextView = itemView.findViewById(R.id.tvTradePrice)
        private val tvSignedChangeRate: TextView = itemView.findViewById(R.id.tvSignedChangeRate)
        private val tvAccTradePrice24h: TextView = itemView.findViewById(R.id.tvAccTradePrice24h)

        fun onBindView(ticker: TickerItem) {

            tvMarket.text = ticker.coinName
            tvTradePrice.text = ticker.last
            tvSignedChangeRate.setTradeDiffColor(ticker.signedChangeRate)
            tvSignedChangeRate.text = ticker.tradeDiff
            tvAccTradePrice24h.text = ticker.tradeAmount

            itemView.setOnClickListener {
                listener?.onItemClick(ticker)
            }

        }

        companion object {
            fun newInstance(parent: ViewGroup, listener: TickerClickListener?) =
                TickerViewHolder(
                    parent.inflate(R.layout.item_ticker),
                    listener
                )
        }
    }

}