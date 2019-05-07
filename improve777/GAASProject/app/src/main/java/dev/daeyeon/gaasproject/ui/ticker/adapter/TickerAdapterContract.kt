package dev.daeyeon.gaasproject.ui.ticker.adapter

import dev.daeyeon.gaasproject.data.source.Ticker

interface TickerAdapterContract {
    interface View {

        fun notifyAdapter()
    }

    interface Model {

        fun replaceList(list: List<Ticker>)

        fun addList(list: List<Ticker>)

        fun clearList()
    }
}