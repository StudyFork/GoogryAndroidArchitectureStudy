package study.architecture.ui.coinjob

import study.architecture.model.vo.ProcessingTicker

interface CoinContract {
    interface View {
        fun notifyAdapter(list: List<ProcessingTicker>)
        fun showProgress()
        fun hideProgress()
    }

    interface Presenter {
        fun onResume()
        fun onPause()
    }
}