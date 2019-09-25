package kr.schoolsharing.coinhelper.util

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import kr.schoolsharing.coinhelper.model.UpbitItem
import kr.schoolsharing.coinhelper.tasks.UpbitRVAdapter

@BindingAdapter("items")
fun RecyclerView.replaceAll(items: List<UpbitItem>) {
    (this.adapter as? UpbitRVAdapter)?.setTickerList(items)
}
