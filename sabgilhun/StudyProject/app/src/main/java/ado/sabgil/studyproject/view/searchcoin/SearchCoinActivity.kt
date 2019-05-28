package ado.sabgil.studyproject.view.searchcoin

import ado.sabgil.studyproject.R
import ado.sabgil.studyproject.adapter.TickerAdapter
import ado.sabgil.studyproject.data.CoinRepositoryImpl
import ado.sabgil.studyproject.databinding.ActivitySearchCoinBinding
import ado.sabgil.studyproject.view.base.BaseActivityViewModel
import android.os.Bundle

class SearchCoinActivity :
    BaseActivityViewModel<ActivitySearchCoinBinding>(R.layout.activity_search_coin) {

    private lateinit var searchCoinViewModel: SearchCoinViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        searchCoinViewModel = SearchCoinViewModel(CoinRepositoryImpl)

        binding.rvTickerList.adapter = TickerAdapter()

        binding.tvSearch.setOnClickListener {
            searchCoinViewModel.searchCoin(binding.etKeyword.text.toString())
        }

        searchCoinViewModel.coinListListeners.add {
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
}