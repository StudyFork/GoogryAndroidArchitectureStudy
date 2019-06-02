package ado.sabgil.studyproject.view.searchcoin

import ado.sabgil.studyproject.R
import ado.sabgil.studyproject.adapter.TickerAdapter
import ado.sabgil.studyproject.data.CoinRepositoryImpl
import ado.sabgil.studyproject.databinding.ActivitySearchCoinBinding
import ado.sabgil.studyproject.view.base.BaseActivity
import android.os.Bundle
import androidx.core.widget.doAfterTextChanged

class SearchCoinActivity :
    BaseActivity<ActivitySearchCoinBinding>(R.layout.activity_search_coin) {

    private lateinit var searchCoinViewModel: SearchCoinViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        progressBar = binding.pgCoinList

        searchCoinViewModel = addingToContainer {
            SearchCoinViewModel(CoinRepositoryImpl)
        }

        bind {
            rvTickerList.adapter = TickerAdapter()

            tvSearch.setOnClickListener {
                searchCoinViewModel.searchCoinByKeyword()
            }

            etKeyword.doAfterTextChanged {
                searchCoinViewModel.keyword = it.toString()
            }
        }

        registerEvent()
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
        searchCoinViewModel.run {
            showToastEventListeners = ::showToastMessage

            showProgressEventListeners = ::showProgressBar

            hideProgressEventListeners = ::hideProgressBar

            coinListListeners = binding::setItem
        }
    }
}