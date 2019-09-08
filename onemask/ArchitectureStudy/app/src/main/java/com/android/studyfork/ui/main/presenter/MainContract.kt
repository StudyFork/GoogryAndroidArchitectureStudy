package com.android.studyfork.ui.main.presenter

/**
 * created by onemask
 */
interface MainContract {
    interface View {
        fun initViewPager()
        fun setViewPagerTitle(titles: Array<String>)
        fun setViewPagerData(items: Array<String>)
    }

    interface Presenter {
        fun loadData()
    }

}