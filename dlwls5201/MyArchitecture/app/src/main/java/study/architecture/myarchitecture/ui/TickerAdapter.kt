package study.architecture.myarchitecture.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import study.architecture.myarchitecture.R
import study.architecture.myarchitecture.network.model.UpbitTicker
import study.architecture.myarchitecture.util.setCoinName
import study.architecture.myarchitecture.util.setLast
import study.architecture.myarchitecture.util.setTradeAmount
import study.architecture.myarchitecture.util.setTradeDiff

class TickerAdapter: RecyclerView.Adapter<TickerAdapter.TickerViewHolder>() {

    private var tickers =  mutableListOf<UpbitTicker>()

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
     * ViewHolder
     */
    class TickerViewHolder(
        itemView: View,
        private val listener: TickerClickListener?) : RecyclerView.ViewHolder(itemView) {

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