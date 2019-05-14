package ado.sabgil.studyproject.view.home

import ado.sabgil.studyproject.view.base.BaseContract

interface HomeContract {

    interface View : BaseContract.View {
        fun updateViewPager(marketList: List<String>)

        fun showProgressBar(flag: Boolean)
    }

    interface Presenter : BaseContract.Presenter {
        fun loadMarketList()
    }

}