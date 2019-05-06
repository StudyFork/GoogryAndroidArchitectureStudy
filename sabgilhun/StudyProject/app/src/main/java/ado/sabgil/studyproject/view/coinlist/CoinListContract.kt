package ado.sabgil.studyproject.view.coinlist

import ado.sabgil.studyproject.data.model.Ticker
import com.example.android.architecture.blueprints.todoapp.BasePresenter
import com.example.android.architecture.blueprints.todoapp.BaseView

interface CoinListContract {
    interface View : BaseView<Presenter> {
        fun showProgressBar(flag : Boolean)
        fun updateList(list : List<Ticker>)
    }

    interface Presenter : BasePresenter {
        fun loadTickers()
    }
}