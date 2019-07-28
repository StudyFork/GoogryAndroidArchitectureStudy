package study.architecture.myarchitecture.ui.main

import android.os.Bundle

interface MainContract {

    interface View {

        fun setViewPagers(pagers: Array<String>)

        fun setViewPagerTitles(titles: Array<String>)
    }

    interface Presenter {

        fun detachView()

        fun loadData()

        fun sendEventBus(bundle: Bundle)
    }
}