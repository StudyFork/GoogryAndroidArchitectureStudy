package study.architecture.ui.coinjob.adapter

import study.architecture.model.vo.ProcessingTicker

interface CoinAdapterContract {
    interface View {
        fun updateList(list: List<ProcessingTicker>)
        fun clearList()
    }

    interface Model {
        fun notifyDataChange()
    }

}