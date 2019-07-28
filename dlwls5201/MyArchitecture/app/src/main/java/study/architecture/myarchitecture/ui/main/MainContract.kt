package study.architecture.myarchitecture.ui.main

interface MainContract {

    interface View {

        fun setViewPagers(pagers: Array<String>)

        fun setViewPagerTitles(titles: Array<String>)

        fun getCoinNameIsSelected(): Boolean

        fun getLastIsSelected(): Boolean

        fun getTradeDiffIsSelected(): Boolean

        fun getTradeAmountIsSelected(): Boolean

        fun setCoinNameIsSelected(selected: Boolean)

        fun setLastIsSelected(selected: Boolean)

        fun setTradeDiffIsSelected(selected: Boolean)

        fun setTradeAmountIsSelected(selected: Boolean)

        fun setCoinNameVisibility(visibility: Int)

        fun setLastVisibility(visibility: Int)

        fun setTradeDiffVisibility(visibility: Int)

        fun setTradeAmountVisibility(visibility: Int)

        /**
         * ViewPager 에서 미리 생성된 프래그먼트의 값을 상단 정렬바 아이템을 누를떄
         * 일괄적으로 변경해 주기 위한 옵저버 패턴 함수
         */
        fun notifyTickerListObervers(field: String, order: Int)
    }

    interface Presenter {

        fun detachView()

        fun loadData()

        fun changeArrow(selectArrow: MainActivity.SelectArrow)
    }
}