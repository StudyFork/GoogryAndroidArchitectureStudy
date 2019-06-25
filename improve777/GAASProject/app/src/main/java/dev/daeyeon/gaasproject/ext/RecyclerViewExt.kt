package dev.daeyeon.gaasproject.ext

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import dev.daeyeon.gaasproject.data.Ticker
import dev.daeyeon.gaasproject.data.source.UpbitDataSource
import dev.daeyeon.gaasproject.ui.ticker.TickerAdapter
import dev.daeyeon.gaasproject.ui.ticker.marketchoice.MarketChoiceAdapter

@BindingAdapter("items")
fun RecyclerView.setItems(items: List<Any>) {
    (adapter as? TickerAdapter)?.replaceList(items as? List<Ticker> ?: emptyList())
    (adapter as? MarketChoiceAdapter)?.replaceAll(items as? List<String> ?: listOf(UpbitDataSource.ALL_CURRENCY))
}