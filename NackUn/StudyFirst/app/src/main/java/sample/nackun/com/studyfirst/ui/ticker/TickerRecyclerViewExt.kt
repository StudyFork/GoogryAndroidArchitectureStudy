package sample.nackun.com.studyfirst.ui.ticker

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import sample.nackun.com.studyfirst.databinding.TickerItemBinding

@BindingAdapter("setItems")
fun setItems(recyclerView: RecyclerView, items: List<Map<String, String>>) {
    val adapter: TickerAdapter<TickerItemBinding>? = recyclerView.adapter as? TickerAdapter<TickerItemBinding>
    adapter?.let {
        it.setItems(items)
        it.notifyDataSetChanged()
    }
}