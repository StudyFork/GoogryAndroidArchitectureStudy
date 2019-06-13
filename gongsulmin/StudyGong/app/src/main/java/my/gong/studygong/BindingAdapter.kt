package my.gong.studygong

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import my.gong.studygong.adapter.CoinAdapter
import my.gong.studygong.adapter.CoinMarketAdapter
import my.gong.studygong.data.model.Ticker

@BindingAdapter("setItems")
fun <T> RecyclerView.setItems(list: T){
    if (list != null){
        if (adapter is CoinAdapter){
            (adapter as CoinAdapter).refreshData(list as List<Ticker>)
        }else if (adapter is CoinMarketAdapter){
            (adapter as CoinMarketAdapter).refreshData(list as List<String>)
        }
    }
}