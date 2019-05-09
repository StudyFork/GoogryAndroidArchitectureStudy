package ado.sabgil.studyproject.view.home

import ado.sabgil.studyproject.view.base.BasePresenter
import ado.sabgil.studyproject.view.base.BaseView

interface HomeContract {

    interface View : BaseView<Presenter> {
        fun updateViewPager(marketList: List<String>)

        fun showProgressBar(flag: Boolean)

        fun showToast(msg: String)

    }

    interface Presenter : BasePresenter

}