package com.jake.archstudy.ui.main

import com.jake.archstudy.base.BaseContract
import com.jake.archstudy.data.model.Market

interface MainContract {

    interface View : BaseContract.View<Presenter> {

        fun setViewPager(markets: List<Market>)

    }

    interface Presenter : BaseContract.Presenter

}