package com.jake.archstudy.ui.tickers

import com.jake.archstudy.base.BasePresenter
import com.jake.archstudy.base.BaseView

interface TickersContract {

    interface View : BaseView<Presenter> {

    }

    interface Presenter : BasePresenter<View> {

    }

}