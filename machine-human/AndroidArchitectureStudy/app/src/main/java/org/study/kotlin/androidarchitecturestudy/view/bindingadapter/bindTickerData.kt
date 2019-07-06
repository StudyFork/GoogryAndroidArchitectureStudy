package org.study.kotlin.androidarchitecturestudy.view.bindingadapter

import android.databinding.BindingAdapter
import android.support.v7.widget.RecyclerView
import org.study.kotlin.androidarchitecturestudy.adapter.recyclerviewadapter.MainListAdapter
import org.study.kotlin.androidarchitecturestudy.api.model.TickerModel

@BindingAdapter("setTickerList")
fun bindTickerListRecyclerView(
    view: RecyclerView,
    list: List<TickerModel>?
) {
    (view.adapter as MainListAdapter)?.run {
        list?.let { setList(it)
            notifyDataSetChanged() }

    }

}