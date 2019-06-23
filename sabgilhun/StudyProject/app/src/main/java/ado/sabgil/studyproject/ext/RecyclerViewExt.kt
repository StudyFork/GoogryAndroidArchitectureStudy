package ado.sabgil.studyproject.ext

import ado.sabgil.studyproject.adapter.TickerAdapter
import ado.sabgil.studyproject.data.model.Ticker
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView

@BindingAdapter("item")
fun RecyclerView.setRecyclerViewItem(item: List<Ticker>?) {
    if (item != null) {
        (adapter as? TickerAdapter)?.let {
            it.update(item)
            it.notifyDataSetChanged()
        }

    }
}