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

        fun sendEventBus(field: String, order: Int)
    }

    interface Presenter {

        fun detachView()

        fun loadData()

        fun changeArrow(selectArrow: MainActivity.SelectArrow)
    }
}