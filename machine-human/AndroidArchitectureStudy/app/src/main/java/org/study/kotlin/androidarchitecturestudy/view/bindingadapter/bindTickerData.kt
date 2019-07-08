package org.study.kotlin.androidarchitecturestudy.view.bindingadapter

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import org.study.kotlin.androidarchitecturestudy.adapter.recyclerviewadapter.MainListAdapter
import org.study.kotlin.androidarchitecturestudy.api.model.TickerModel

@BindingAdapter("setTickerList")
fun bindTickerListRecyclerView(
    view: androidx.recyclerview.widget.RecyclerView,
    list: List<TickerModel>?
) {
    (view.adapter as MainListAdapter)?.run {
        list?.let { setList(it)
            notifyDataSetChanged() }

    }

}