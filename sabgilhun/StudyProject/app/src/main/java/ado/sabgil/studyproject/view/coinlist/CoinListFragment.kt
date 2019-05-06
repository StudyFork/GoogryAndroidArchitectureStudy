package ado.sabgil.studyproject.view.coinlist

import ado.sabgil.studyproject.data.model.Ticker
import androidx.fragment.app.Fragment

class CoinListFragment :Fragment(), CoinListContract.View{
    override lateinit var presenter: CoinListContract.Presenter

    override fun showProgressBar(flag: Boolean) {
    }

    override fun updateList(list: List<Ticker>) {
    }
}