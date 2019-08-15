package study.architecture.myarchitecture.ui.main

interface MainContract {

    interface View {

        fun showViewPagers(pagers: Array<String>)

        fun showViewPagerTitles(titles: Array<String>)

        fun getArrowIsSelected(selectArrow: MainActivity.SelectArrow): Boolean

        fun setArrowSelected(selectArrow: MainActivity.SelectArrow, selected: Boolean)

        fun setArrowVisibility(selectArrow: MainActivity.SelectArrow, visibility: Int)

        /**
         * ViewPager 에서 미리 생성된 프래그먼트의 값을 상단 정렬바 아이템을 누를떄
         * 일괄적으로 변경해 주기 위한 옵저버 패턴 함수
         */
        fun showTickerListOrderByField(field: String, order: Int)
    }

    interface Presenter {

        fun detachView()

        fun loadData()

        fun changeArrow(selectArrow: MainActivity.SelectArrow)
    }
}