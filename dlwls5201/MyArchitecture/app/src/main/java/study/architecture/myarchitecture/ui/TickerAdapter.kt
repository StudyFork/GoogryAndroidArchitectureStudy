package study.architecture.myarchitecture.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import study.architecture.myarchitecture.R
import study.architecture.myarchitecture.network.model.UpbitTicker
import study.architecture.myarchitecture.util.*

class TickerAdapter : RecyclerView.Adapter<TickerAdapter.TickerViewHolder>() {

    companion object {


        const val KEY_ORDER = "order"

        const val ASC = 0   //오름차순

        const val DESC = 1  //내림차순


        const val KEY_FIELD = "field"

        const val COIN_NAME = "코인명"

        const val LAST = "현재가"

        const val TRADE_DIFF = "전일대비"

        const val TRADE_AMOUNT = "거래대금"
    }

    private var tickers = mutableListOf<UpbitTicker>()

    private var listener: TickerClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TickerViewHolder {
        return TickerViewHolder.newInstance(parent, listener)
    }

    override fun getItemCount() = tickers.size

    override fun onBindViewHolder(holder: TickerViewHolder, position: Int) {
        holder.onBindView(tickers[position])
    }

    fun setTickerClickListener(listener: TickerClickListener) {
        this.listener = listener
    }

    fun setItem(newTickers: MutableList<UpbitTicker>) {
        this.tickers = newTickers
        notifyDataSetChanged()
    }

    interface TickerClickListener {

        fun onItemClick(ticker: UpbitTicker)
    }

    /**
     * 정렬
     */
    fun orderByField(bundle: Bundle) {

        val order = bundle.getInt(KEY_ORDER)

        val field = bundle.getString(KEY_FIELD)

        //Dlog.d("order : $order , field : $field")
        when (field) {

            COIN_NAME -> {
                val selector: (UpbitTicker) -> String = { it.market }
                setOrderByField(selector, order)
            }

            LAST -> {
                val selector: (UpbitTicker) -> Double = { it.tradePrice }
                setOrderByField(selector, order)
            }

            TRADE_DIFF -> {
                val selector: (UpbitTicker) -> Double = { it.signedChangeRate }
                setOrderByField(selector, order)
            }

            TRADE_AMOUNT -> {
                val selector: (UpbitTicker) -> Double = { it.accTradePrice24h }
                setOrderByField(selector, order)
            }
        }
    }

    private fun <R : Comparable<R>>setOrderByField(selector: (UpbitTicker) -> R, order: Int) {
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

        fun onBindView(ticker: UpbitTicker) {

            tvMarket.setCoinName(ticker.market)
            tvTradePrice.setLast(ticker.tradePrice)
            tvSignedChangeRate.setTradeDiff(ticker.signedChangeRate)
            tvAccTradePrice24h.setTradeAmount(ticker.accTradePrice24h)

            itemView.setOnClickListener {
                listener?.onItemClick(ticker)
            }

        }

        companion object {
            fun newInstance(parent: ViewGroup, listener: TickerClickListener?): TickerViewHolder {

                return TickerViewHolder(
                    LayoutInflater.from(parent.context).inflate(R.layout.item_ticker, parent, false),
                    listener
                )

            }
        }
    }

}