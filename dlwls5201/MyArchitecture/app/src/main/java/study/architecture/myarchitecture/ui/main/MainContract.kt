package study.architecture.myarchitecture.ui.main

interface MainContract {

    interface View {

        fun showViewPagers(pagers: Array<String>)

        fun showViewPagerTitles(titles: Array<String>)

        fun showCategoryAllow(selectArrow: MainActivity.SelectArrow)
    }

    interface Presenter {

        fun detachView()

        fun loadData()

        fun changeArrow(selectArrow: MainActivity.SelectArrow)
    }
}