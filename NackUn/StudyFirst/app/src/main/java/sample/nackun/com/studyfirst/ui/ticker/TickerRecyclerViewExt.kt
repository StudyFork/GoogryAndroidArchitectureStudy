package sample.nackun.com.studyfirst.ui.ticker

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView

@BindingAdapter("setItems")
fun setItems(recyclerView: RecyclerView, items: List<Map<String, String>>) {
    val adapter: TickerAdapter? = recyclerView.adapter as? TickerAdapter
    adapter?.let {
        it.setItems(items)
        it.notifyDataSetChanged()
    }
}