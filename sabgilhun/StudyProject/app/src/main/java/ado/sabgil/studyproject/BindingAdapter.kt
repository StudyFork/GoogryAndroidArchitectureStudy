package ado.sabgil.studyproject

import ado.sabgil.studyproject.adapter.TickerAdapter
import ado.sabgil.studyproject.data.model.Ticker
import androidx.recyclerview.widget.RecyclerView
import androidx.databinding.BindingAdapter

object BindingAdapter {
    @BindingAdapter("item")
    @JvmStatic fun setRecyclerViewItem(view: RecyclerView, item: List<Ticker>?) {
        (view.adapter as TickerAdapter).submitList(item)
    }
}