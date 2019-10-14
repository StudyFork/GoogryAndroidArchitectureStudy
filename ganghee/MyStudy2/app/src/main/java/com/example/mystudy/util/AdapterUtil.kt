package com.example.mystudy.util

import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.mystudy.R
import com.example.mystudy.base.BaseRecyclerViewAdapter
import com.example.mystudy.data.FormatTickers
import org.w3c.dom.Text

@BindingAdapter("replaceAll")
fun RecyclerView.replaceAll(list: List<Any>?) {
    @Suppress("UNCHECKED_CAST")
    (this.adapter as? BaseRecyclerViewAdapter<Any, *>)?.run {
        replaceAll(list)
        notifyDataSetChanged()
    }
}

@BindingAdapter(value = ["market"])
fun TextView.setMarket(tickers: FormatTickers){
    text = tickers.toMarket?.run {
        split("-")[1]
    }
}

@BindingAdapter(value = ["tradePrice"])
fun TextView.setTradePrice(tickers: FormatTickers){
    text = tickers.toTradePrice?.run {
        when{
            this > 100 -> String.format("%,.0f",this)
            this > 10 -> String.format("%,.1f",this)
            this > 1 -> String.format("%,.2f",this)
            else -> String.format("%,.8f",this)
        }
    }
}

@BindingAdapter(value = ["changeRate"])
fun TextView.setChangeRate(tickers: FormatTickers){
    text = tickers.toSignedChangeRate?.run {
        when{
            this > 0 -> {
                setTextColor(ResourcesCompat.getColor(resources, R.color.diff_up,null))
            }
            this < 0 -> {
                setTextColor(ResourcesCompat.getColor(resources, R.color.diff_down,null))
            }
            else -> {
                setTextColor(ResourcesCompat.getColor(resources, R.color.black,null))
            }
        }
        String.format("%.2f%%",this)
    } ?: ""
}

@BindingAdapter(value = ["tradePrice24th"])
fun TextView.setTradePrice24th(tickers: FormatTickers){
    val firstMarket = tickers.toMarket?.run { split("-")[0] }
    text = tickers.toAccTradePrice24h?.run {
        when (firstMarket){
            "KRW" -> String.format("%,.0f",tickers.toAccTradePrice24h/1000000) + "M"
            "USDT" ->
                if (tickers.toAccTradePrice24h > 1000000)
                    String.format("%,.0f",tickers.toAccTradePrice24h)
                else
                    String.format("%,.0f",tickers.toAccTradePrice24h)
            else -> String.format("%,.3f",tickers.toAccTradePrice24h)
        }
    }
}