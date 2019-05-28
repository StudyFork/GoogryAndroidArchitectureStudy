package ado.sabgil.studyproject.view.coinlist

import ado.sabgil.studyproject.R
import ado.sabgil.studyproject.adapter.TickerAdapter
import ado.sabgil.studyproject.data.CoinRepositoryImpl
import ado.sabgil.studyproject.databinding.FragmnetCoinListBinding
import ado.sabgil.studyproject.view.base.BaseFragmentViewModel
import android.os.Bundle
import android.view.View

class CoinListFragment : BaseFragmentViewModel<FragmnetCoinListBinding>(R.layout.fragmnet_coin_list) {

    private lateinit var coinListViewModel: CoinListViewModel

    private val baseCurrency: String? by lazy {
        arguments?.getString(ARGUMENT_BASE_CURRENCY)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvTickerList.adapter = TickerAdapter()

        coinListViewModel = CoinListViewModel(baseCurrency!!, CoinRepositoryImpl)

        coinListViewModel.coinListListeners.add {
            binding.item = it
        }
    }

    override fun onResume() {
        super.onResume()
        coinListViewModel.subscribeRemote()
    }

    override fun onPause() {
        coinListViewModel.unSubscribeRemote()
        super.onPause()
    }

    companion object {
        const val ARGUMENT_BASE_CURRENCY = "BASE_CURRENCY"
        fun newInstance(baseCurrency: String) =
            CoinListFragment().apply {
                arguments = Bundle().apply {
                    putString(ARGUMENT_BASE_CURRENCY, baseCurrency)
                }
            }
    }

}