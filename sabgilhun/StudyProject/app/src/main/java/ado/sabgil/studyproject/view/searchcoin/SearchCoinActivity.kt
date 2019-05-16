package ado.sabgil.studyproject.view.searchcoin

import ado.sabgil.studyproject.R
import ado.sabgil.studyproject.data.CoinRepositoryImpl
import ado.sabgil.studyproject.data.model.Ticker
import ado.sabgil.studyproject.databinding.ActivitySearchCoinBinding
import ado.sabgil.studyproject.view.base.BaseActivity

class SearchCoinActivity :
    BaseActivity<ActivitySearchCoinBinding, SearchCoinContract.Presenter>(R.layout.activity_search_coin),
    SearchCoinContract.View {

    override fun createPresenter() = SearchCoinPresenter(this, CoinRepositoryImpl)

    override fun updateCoinList(list: List<Ticker>) {
    }
}