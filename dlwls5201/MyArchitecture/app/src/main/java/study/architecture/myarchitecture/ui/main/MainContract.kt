package study.architecture.myarchitecture.ui.main

import study.architecture.myarchitecture.util.Filter

interface MainContract {

    interface View {

        fun showViewPagers(pagers: Array<String>)

        fun showViewPagerTitles(titles: Array<String>)

        fun showCategoryArrow(selectArrow: Filter.SelectArrow)
    }

    interface Presenter {

        fun detachView()

        fun loadData()
    }
}