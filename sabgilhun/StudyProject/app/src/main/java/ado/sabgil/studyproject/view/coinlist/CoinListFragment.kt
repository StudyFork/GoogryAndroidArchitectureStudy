package ado.sabgil.studyproject.view.coinlist

import ado.sabgil.studyproject.R
import ado.sabgil.studyproject.adapter.TickerAdapter
import ado.sabgil.studyproject.data.CoinRepositoryImpl
import ado.sabgil.studyproject.data.model.Ticker
import ado.sabgil.studyproject.databinding.FragmnetCoinListBinding
import ado.sabgil.studyproject.view.base.BaseFragment
import android.os.Bundle
import android.view.View

class CoinListFragment : BaseFragment<FragmnetCoinListBinding, CoinListContract.Presenter>(R.layout.fragmnet_coin_list),
    CoinListContract.View {

    override fun createPresenter(): CoinListContract.Presenter = CoinListPresenter(
        requireNotNull(baseCurrency),
        this,
        CoinRepositoryImpl
    )

    private val baseCurrency: String? by lazy {
        arguments?.getString(ARGUMENT_BASE_CURRENCY)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvTickerList.adapter = TickerAdapter()
    }

    override fun updateCoinList(list: List<Ticker>) {
        binding.it = list
    }

    override fun onResume() {
        super.onResume()
        presenter.subscribeRemote()
    }

    override fun onPause() {
        presenter.unSubscribeRemote()
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