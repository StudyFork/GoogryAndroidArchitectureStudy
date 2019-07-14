package org.study.kotlin.androidarchitecturestudy.view.bindingadapter

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import org.study.kotlin.androidarchitecturestudy.base.BaseRecyclerView

@Suppress("UNCHECKED_CAST")
@BindingAdapter("replaceAll")
fun RecyclerView.replaceAll(list: List<Any>?) {
    (this.adapter as? BaseRecyclerView.Adapter<Any, *>)?.run {
        list?.let {
            replaceAll(it)
            notifyDataSetChanged()
        }
    }
}

