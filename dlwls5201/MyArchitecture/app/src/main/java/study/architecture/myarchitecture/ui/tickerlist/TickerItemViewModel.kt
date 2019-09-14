package study.architecture.myarchitecture.ui.tickerlist

import androidx.databinding.ObservableField
import study.architecture.myarchitecture.ui.model.TickerItem

class TickerItemViewModel(
    private val item: TickerItem
) {

    val tickerItem = ObservableField<TickerItem>()

    init {
        tickerItem.set(item)
    }

}