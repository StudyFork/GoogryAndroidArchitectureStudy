package sample.nackun.com.studyfirst.ui

import android.graphics.Color
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.ticker_item.view.*
import sample.nackun.com.studyfirst.R
import sample.nackun.com.studyfirst.util.TickerFormatter
import sample.nackun.com.studyfirst.vo.Ticker

class TickerAdapter(
    //@LayoutRes private val layoutRes: Int
) : RecyclerView.Adapter<TickerAdapter.TickerViewHolder>() {

    protected val items = mutableListOf<Ticker>()

    fun setItems(tickers: List<Ticker>) {
        items.clear()
        items.addAll(tickers)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        TickerViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.ticker_item, parent, false))


    override fun getItemCount() = items.size

    override fun onBindViewHolder(tickerViewHolder: TickerViewHolder, position: Int) =
        tickerViewHolder.bind(items[position])

    inner class TickerViewHolder(
//        @LayoutRes layoutRes: Int,
//    parent: ViewGroup
        view: View
    ) : RecyclerView.ViewHolder(
//        LayoutInflater.from(parent.context)
//            .inflate(layoutRes, parent, false)
        view
    ) {
        val tickerName = view.tickerName
        val currentPrice = view.currentPrice
        val comparePrice = view.comparePrice
        val changePrice = view.changePrice

        fun bind(item: Ticker) {
            tickerName.text = TickerFormatter.convertTo(item)["tickerName"]
            currentPrice.text = TickerFormatter.convertTo(item)["currentPrice"]
            comparePrice.text = TickerFormatter.convertTo(item)["comparePrice"]
            comparePrice.setTextColor(TickerFormatter.convertTo(item)["compareColor"]!!.toInt())
            changePrice.text = TickerFormatter.convertTo(item)["changePrice"]
        }
    }
}
