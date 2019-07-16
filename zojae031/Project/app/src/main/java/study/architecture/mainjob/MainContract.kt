package study.architecture.mainjob

import study.architecture.vo.Ticker

interface MainContract {
    interface View {
        fun notifyAdapter(list: List<Ticker>)
    }

    interface Presenter {
        fun onCreate()

    }
}