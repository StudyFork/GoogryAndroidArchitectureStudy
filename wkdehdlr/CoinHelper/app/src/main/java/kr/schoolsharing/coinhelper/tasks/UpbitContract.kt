package kr.schoolsharing.coinhelper.tasks

import kr.schoolsharing.coinhelper.BasePresenter
import kr.schoolsharing.coinhelper.BaseView
import kr.schoolsharing.coinhelper.model.UpbitItem

interface UpbitContract {

    interface View : BaseView<Presenter> {

        fun showAddTask(tickerList: List<UpbitItem>)

    }

    interface Presenter : BasePresenter {

        fun completeTask()
    }
}