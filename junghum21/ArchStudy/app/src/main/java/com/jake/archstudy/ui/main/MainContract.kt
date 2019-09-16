package com.jake.archstudy.ui.main

import com.jake.archstudy.base.BasePresenter
import com.jake.archstudy.base.BaseView
import com.jake.archstudy.data.model.Market

interface MainContract {

    interface View : BaseView<Presenter> {

        fun setViewPager(markets: List<Market>)

    }

    interface Presenter : BasePresenter<View>

}