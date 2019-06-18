package dev.daeyeon.gaasproject.ext

import androidx.databinding.BindingAdapter
import dev.daeyeon.gaasproject.data.Ticker
import dev.daeyeon.gaasproject.ui.ticker.TickerAdapter

@BindingAdapter("items")
fun androidx.recyclerview.widget.RecyclerView.setItems(items: List<Ticker>) {
    (adapter as? TickerAdapter)?.replaceList(items)
}