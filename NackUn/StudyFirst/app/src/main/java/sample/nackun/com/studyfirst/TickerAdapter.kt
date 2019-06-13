package sample.nackun.com.studyfirst

import android.content.Context
import android.graphics.Color
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.ticker_item.view.*
import sample.nackun.com.studyfirst.market.Ticker

class TickerAdapter(val items: ArrayList<Ticker>, val context: Context) : RecyclerView.Adapter<TickerViewHolder>() {

    var color: String = ""

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): TickerViewHolder {
        return TickerViewHolder(LayoutInflater.from(context).inflate(R.layout.ticker_item, parent, false))
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(tickerViewHolder: TickerViewHolder, p1: Int) {
        tickerViewHolder.tickerName.text = items[p1].market.substring(
            items.get(p1).market.indexOf("-") + 1,
            items.get(p1).market.length
        )
        when (items.get(p1).market.substring(0, items.get(p1).market.indexOf("-"))) {
            "KRW" -> {
                tickerViewHolder.currentPrice.text = String.format("%,d", items.get(p1).tradePrice.toInt())
                tickerViewHolder.comparePrice.text = compareToBefore(
                    items.get(p1).tradePrice,
                    items.get(p1).prevClosingPrice
                )
                tickerViewHolder.comparePrice.setTextColor(Color.parseColor(color))
                tickerViewHolder.changePrice.text = String.format(
                    "%,d",
                    (items.get(p1).accTradePrice24h / 1000000).toInt()
                ) + " M"
            }
            "BTC" -> {
                tickerViewHolder.currentPrice.text = String.format("%,.8f", items.get(p1).tradePrice)
                tickerViewHolder.comparePrice.text = compareToBefore(
                    items.get(p1).tradePrice,
                    items.get(p1).prevClosingPrice
                )
                tickerViewHolder.comparePrice.setTextColor(Color.parseColor(color))
                tickerViewHolder.changePrice.text = String.format(
                    "%,.3f",
                    items.get(p1).accTradePrice24h
                )
            }
            "ETH" -> {
                if (items.get(p1).tradePrice > 1) {
                    tickerViewHolder.currentPrice.text = String.format(
                        "%,.2f",
                        items.get(p1).tradePrice
                    )
                } else {
                    tickerViewHolder.currentPrice.text = String.format("%,.8f", items.get(p1).tradePrice)
                }
                tickerViewHolder.comparePrice.text = compareToBefore(
                    items.get(p1).tradePrice,
                    items.get(p1).prevClosingPrice
                )
                tickerViewHolder.comparePrice.setTextColor(Color.parseColor(color))
                tickerViewHolder.changePrice.text = String.format(
                    "%,.3f",
                    items.get(p1).accTradePrice24h
                )
            }
            "USDT" -> {
                if (items.get(p1).tradePrice < 1) {
                    tickerViewHolder.currentPrice.text = String.format("%,.8f", items.get(p1).tradePrice)
                } else {
                    tickerViewHolder.currentPrice.text = String.format("%,.2f", items.get(p1).tradePrice)
                }
                if (tickerViewHolder.currentPrice.text.endsWith("0"))
                    tickerViewHolder.currentPrice.text =
                        tickerViewHolder.currentPrice.text.substring(0, tickerViewHolder.currentPrice.text.length - 1)

                tickerViewHolder.comparePrice.text = compareToBefore(
                    items.get(p1).tradePrice,
                    items.get(p1).prevClosingPrice
                )
                tickerViewHolder.comparePrice.setTextColor(Color.parseColor(color))
                if (items.get(p1).accTradePrice24h > 1000000) {
                    tickerViewHolder.changePrice.text = String.format(
                        "%,d",
                        (items.get(p1).accTradePrice24h / 1000).toInt()
                    ) + " K"
                } else {
                    tickerViewHolder.changePrice.text = String.format(
                        "%,d",
                        items.get(p1).accTradePrice24h.toInt()
                    )
                }
            }
        }
    }

    fun compareToBefore(currentPrice: Double, beforePrice: Double): String {
        var percent = Math.abs(currentPrice - beforePrice) / beforePrice
        percent *= 100
        if (currentPrice - beforePrice > 0) {
            color = "#FF0000"
            return String.format("%.2f", percent) + "%"
        } else if (currentPrice == beforePrice) {
            color = "#000000"
            return String.format("%.2f", percent) + "%"
        } else {
            color = "#0000FF"
            return "-" + String.format("%.2f", percent) + "%"
        }
    }
}

class TickerViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val tickerName = view.tickerName
    val currentPrice = view.currentPrice
    val comparePrice = view.comparePrice
    val changePrice = view.changePrice
}