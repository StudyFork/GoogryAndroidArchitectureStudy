package sample.nackun.com.studyfirst.ui.ticker

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.ticker_item.view.*
import sample.nackun.com.studyfirst.R

class TickerAdapter : RecyclerView.Adapter<TickerAdapter.TickerViewHolder>() {

    private val items = mutableListOf<Map<String, String>>()

    fun setItems(tickers: List<Map<String, String>>) {
        items.clear()
        items.addAll(tickers)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        TickerViewHolder(parent)

    override fun getItemCount() = items.size

    override fun onBindViewHolder(tickerViewHolder: TickerViewHolder, position: Int) =
        items[position].let(tickerViewHolder::bind)

    inner class TickerViewHolder(
        parent: ViewGroup
    ) : RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context)
            .inflate(R.layout.ticker_item, parent, false)
    ) {
        val tickerName = itemView.tickerName
        val currentPrice = itemView.currentPrice
        val comparePrice = itemView.comparePrice
        val changePrice = itemView.changePrice

        fun bind(item: Map<String, String>) {
            tickerName.text = item["tickerName"]
            currentPrice.text = item["currentPrice"]
            comparePrice.text = item["comparePrice"]
            comparePrice.setTextColor(item["compareColor"]!!.toInt())
            changePrice.text = item["changePrice"]
        }
    }
}
