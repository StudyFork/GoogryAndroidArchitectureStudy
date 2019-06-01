package ado.sabgil.studyproject.view.searchcoin

import ado.sabgil.studyproject.R
import ado.sabgil.studyproject.adapter.TickerAdapter
import ado.sabgil.studyproject.data.CoinRepositoryImpl
import ado.sabgil.studyproject.databinding.ActivitySearchCoinBinding
import ado.sabgil.studyproject.ext.setTextChangeListener
import ado.sabgil.studyproject.view.base.BaseActivity
import android.os.Bundle

class SearchCoinActivity :
    BaseActivity<ActivitySearchCoinBinding>(R.layout.activity_search_coin) {

    private lateinit var searchCoinViewModel: SearchCoinViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        searchCoinViewModel = SearchCoinViewModel(CoinRepositoryImpl).apply { viewModelContainer.add(this) }

        progressBar = binding.pgCoinList

        binding.rvTickerList.adapter = TickerAdapter()

        binding.tvSearch.setOnClickListener {
            searchCoinViewModel.searchCoinByKeyword()
        }

        binding.etKeyword.setTextChangeListener {
            searchCoinViewModel.keyword = it
        }

        registerEvent()

        searchCoinViewModel.coinListListeners = {
            binding.item = it
        }
    }

    override fun onResume() {
        super.onResume()
        searchCoinViewModel.subscribeCoinData()
    }

    override fun onPause() {
        searchCoinViewModel.unSubscribeCoinData()
        super.onPause()
    }

    private fun registerEvent() {
        searchCoinViewModel.showToastEventListeners = ::showToastMessage

        searchCoinViewModel.showProgressEventListeners = ::showProgressBar

        searchCoinViewModel.hideProgressEventListeners = ::hideProgressBar
    }
}