package kr.schoolsharing.coinhelper.util

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import kr.schoolsharing.coinhelper.base.BaseRecyclerViewAdapter

@BindingAdapter("items")
fun RecyclerView.replaceAll(items: List<Any>?) {
    @Suppress("UNCHECKED_CAST")
    (adapter as? BaseRecyclerViewAdapter<Any, *>)?.run {
        if (items != null) {
            setTickerList(items)
        }
    }
}
