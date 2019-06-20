package org.study.kotlin.androidarchitecturestudy.view.fragment.main

import android.util.Log
import org.study.kotlin.androidarchitecturestudy.api.model.TickerModel
import org.study.kotlin.androidarchitecturestudy.base.BasePresenter
import org.study.kotlin.androidarchitecturestudy.base.BaseView

/**
***************************
Contract - structure

i = interface
f = function
v = variable
***************************

i = MainContract
    i = View : BaseView<Presenter>
        f = setTickers(tickers: ArrayList<TickerModel>)

    i = Presenter : BasePresenter
        f = requestDataFromTickerRepository(marketName: String)

 */
//Presenter 와 V는 1대 1 관계 (클래스 자체가 아니라 Contract라는 interface를 통해 서로를 알고 있음)
interface MainContract {

    /**
        i = BaseView<T>

            v = presenter: T
            f = showProgress(text: String)
            f = hideProgress()
     */
    interface View : BaseView<Presenter> {
        fun setTickers(tickers: ArrayList<TickerModel>) {
            Log.e("TAG MainContract", "setTickers")
        }

    }

    /**
        i = BasePresenter

            f = fun start()
     */
    interface Presenter : BasePresenter {
        fun requestDataFromTickerRepository(marketName: String) {
            Log.e("TAG MainContract", "requestDataFromTickerRepository")
        }
    }

}