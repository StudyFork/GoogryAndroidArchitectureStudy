package study.architecture.myarchitecture.ui.tickerlist

import androidx.databinding.ObservableField
import study.architecture.myarchitecture.ui.model.TickerItem

class TickerItemViewModel(
    item: TickerItem
) {

    val tickerItem = ObservableField<TickerItem>()

    init {
        tickerItem.set(item)
    }

}