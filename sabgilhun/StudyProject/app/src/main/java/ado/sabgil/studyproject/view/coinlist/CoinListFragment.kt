package ado.sabgil.studyproject.view.coinlist

import ado.sabgil.studyproject.R
import ado.sabgil.studyproject.adapter.TickerAdapter
import ado.sabgil.studyproject.data.model.Ticker
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
    }

    override fun onResume() {
        super.onResume()
        presenter.start()
    }

    override fun showProgressBar(flag: Boolean) {
        if (flag) binding.pg.visibility = View.VISIBLE
        else binding.pg.visibility = View.INVISIBLE
    }

    override fun updateList(list: List<Ticker>) {
        binding.it = list
    }
}