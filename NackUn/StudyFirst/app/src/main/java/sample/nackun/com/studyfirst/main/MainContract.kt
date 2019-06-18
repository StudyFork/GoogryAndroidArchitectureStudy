package sample.nackun.com.studyfirst.main

import sample.nackun.com.studyfirst.BasePresenter
import sample.nackun.com.studyfirst.BaseView
import sample.nackun.com.studyfirst.vo.Market

interface MainContract {
    interface View : BaseView<Presenter>{
        var marketName: String
    }

    interface Presenter : BasePresenter {

    }
}