package sample.nackun.com.studyfirst.ui.ticker

import android.databinding.BindingAdapter
import android.support.v7.widget.RecyclerView

@BindingAdapter("app:setItems")
fun setItems(recyclerView: RecyclerView, items: List<Map<String, String>>) {
    val adapter: TickerAdapter? = recyclerView.adapter as? TickerAdapter
    adapter?.let {
        it.setItems(items)
        it.notifyDataSetChanged()
    }
}