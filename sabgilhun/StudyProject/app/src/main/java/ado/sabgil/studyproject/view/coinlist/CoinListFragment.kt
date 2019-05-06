package ado.sabgil.studyproject.view.coinlist

import ado.sabgil.studyproject.R
import ado.sabgil.studyproject.adapter.TickerAdapter
import ado.sabgil.studyproject.data.model.Ticker
import ado.sabgil.studyproject.data.remote.upbit.UpbitApiHandler
import ado.sabgil.studyproject.data.remote.upbit.UpbitApiHandlerImpl
import ado.sabgil.studyproject.databinding.FragmnetCoinListBinding
import ado.sabgil.studyproject.enums.BaseCurrency
import ado.sabgil.studyproject.view.base.BaseFragment
import android.os.Bundle
import android.view.View

class CoinListFragment : BaseFragment<FragmnetCoinListBinding>(), CoinListContract.View {

    override lateinit var presenter: CoinListContract.Presenter

    override fun getLayout() = R.layout.fragmnet_coin_list

    private val baseCurrency: BaseCurrency? by lazy {
        arguments?.getString(ARGUMENT_BASE_CURRENCY)?.let { BaseCurrency.valueOf(it) }
    }

    companion object {
        const val ARGUMENT_BASE_CURRENCY = "BASE_CURRENCY"
        fun newInstance(baseCurrency: BaseCurrency) =
            CoinListFragment().apply {
                arguments = Bundle().apply {
                    putString(ARGUMENT_BASE_CURRENCY, baseCurrency.toString())
                }
            }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        CoinListPresenter(requireNotNull(baseCurrency), this)

        binding.rvTickerList.adapter = TickerAdapter()

        UpbitApiHandlerImpl.getAllTickers(BaseCurrency.KRW,
            { result ->
                binding.it = result.map { Ticker.from(it) }.toMutableList()
            },
            {})

    }


    override fun showProgressBar(flag: Boolean) {
    }

    override fun updateList(list: List<Ticker>) {
    }


}