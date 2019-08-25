package study.architecture.myarchitecture.ui.tickerlist

import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import study.architecture.myarchitecture.R
import study.architecture.myarchitecture.ui.model.TickerItem
import study.architecture.myarchitecture.util.inflate
import study.architecture.myarchitecture.util.setTradeDiffColor

class TickerAdapter(
    private val clickEvent: (ticker: TickerItem) -> Unit
) : RecyclerView.Adapter<TickerAdapter.TickerViewHolder>() {

    private val tickers = mutableListOf<TickerItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TickerViewHolder {

        val holder = TickerViewHolder(
            parent.inflate(R.layout.item_ticker)
        )

        holder.itemView.setOnClickListener {
            clickEvent.invoke(tickers[holder.adapterPosition])
        }

        return holder
    }

    override fun getItemCount() = tickers.size

    override fun onBindViewHolder(holder: TickerViewHolder, position: Int) {
        holder.onBindView(tickers[position])
    }

    fun getCopyItems(): MutableList<TickerItem> {
        val copyItems = mutableListOf<TickerItem>()
        copyItems.addAll(tickers)
        return copyItems
    }

    fun setItems(newTickers: MutableList<TickerItem>) {
        tickers.clear()
        tickers.addAll(newTickers)
        notifyDataSetChanged()
    }

    /**
     * ViewHolder
     */
    class TickerViewHolder(
        itemView: View
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
        }
    }

}