package study.architecture.mainjob

import study.architecture.vo.ProcessingTicker

interface MainContract {
    interface View {
        fun notifyAdapter(list: MutableList<ProcessingTicker>)
    }

    interface Presenter {
        fun onResume()
        fun onPause()
    }
}