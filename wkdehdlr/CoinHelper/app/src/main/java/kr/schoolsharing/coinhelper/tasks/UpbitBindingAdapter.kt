package kr.schoolsharing.coinhelper.tasks

import androidx.databinding.BindingAdapter
import androidx.databinding.ObservableField
import androidx.recyclerview.widget.RecyclerView
import kr.schoolsharing.coinhelper.model.UpbitItem

object UpbitBindingAdapter {

    @BindingAdapter("items")
    fun RecyclerView.replaceAll(items: ObservableField<List<UpbitItem>>) {
        (this.adapter as? UpbitRVAdapter)?.let {
            it.setTickerList(items.get()!!)
        }
    }}