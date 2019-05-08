package ado.sabgil.studyproject.view.home

interface HomeContract {

    interface View {
        var presenter: Presenter

        fun updateViewPager(marketList: List<String>)

        fun showProgressBar(flag: Boolean)

        fun showToast(msg: String)

    }

    interface Presenter {
        fun loadMarketData()

    }
}