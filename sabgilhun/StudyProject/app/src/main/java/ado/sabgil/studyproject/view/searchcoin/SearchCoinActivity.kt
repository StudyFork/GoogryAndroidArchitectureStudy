package ado.sabgil.studyproject.view.searchcoin

import ado.sabgil.studyproject.R
import ado.sabgil.studyproject.adapter.SearchedCoinTickerAdapter
import ado.sabgil.studyproject.data.CoinRepositoryImpl
import ado.sabgil.studyproject.data.model.Ticker
import ado.sabgil.studyproject.databinding.ActivitySearchCoinBinding
import ado.sabgil.studyproject.view.base.BaseActivity
import android.os.Bundle

class SearchCoinActivity :
    BaseActivity<ActivitySearchCoinBinding, SearchCoinContract.Presenter>(R.layout.activity_search_coin),
    SearchCoinContract.View {

    override fun createPresenter() = SearchCoinPresenter(this, CoinRepositoryImpl)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.rvTickerList.adapter = SearchedCoinTickerAdapter()

        binding.tvSearch.setOnClickListener {
            presenter.searchCoin(binding.etKeyword.text.toString())
        }
    }

    override fun updateCoinList(list: List<Ticker>) {
        binding.it = list
    }

    override fun onResume() {
        super.onResume()
        presenter.subscribeCoinData()
    }

    override fun onPause() {
        presenter.unSubscribeCoinData()
        super.onPause()
    }
}