package sample.nackun.com.studyfirst.ui.ticker

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import sample.nackun.com.studyfirst.base.BaseRecyclerView

@Suppress("UNCHECKED_CAST")
@BindingAdapter("setItems")
fun RecyclerView.setItems(items: List<Map<String, String>>) {
    (this.adapter as? BaseRecyclerView.BaseAdapter<Any, *>)?.run {
        setItems(items)
        notifyDataSetChanged()
    }
}