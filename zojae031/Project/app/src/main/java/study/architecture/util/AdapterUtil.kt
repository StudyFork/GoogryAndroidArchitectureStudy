package study.architecture.util

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import study.architecture.base.BaseRecyclerViewAdapter


@BindingAdapter("replaceAll")
fun RecyclerView.replaceAll(list: List<Any>?) {
    if (list != null) {
        (this.adapter as? BaseRecyclerViewAdapter<Any, *>)?.updateLists(list)
    }
}