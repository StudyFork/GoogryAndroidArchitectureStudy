package ado.sabgil.studyproject

import ado.sabgil.studyproject.adapter.TickerAdapter
import ado.sabgil.studyproject.data.model.Ticker
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView

object BindingAdapter {
    @BindingAdapter("item")
    @JvmStatic
    fun setRecyclerViewItem(view: RecyclerView, item: List<Ticker>?) {
        if (item != null) {
            (view.adapter as? TickerAdapter)?.submitList(item)
        }
    }
}