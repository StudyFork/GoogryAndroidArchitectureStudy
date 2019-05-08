package ado.sabgil.studyproject.view.coinlist

import ado.sabgil.studyproject.R
import ado.sabgil.studyproject.adapter.TickerAdapter
import ado.sabgil.studyproject.data.model.Ticker
import ado.sabgil.studyproject.databinding.FragmnetCoinListBinding
import ado.sabgil.studyproject.view.base.BaseFragment
import android.os.Bundle
import android.view.View
import android.widget.Toast

class CoinListFragment : BaseFragment<FragmnetCoinListBinding>(), CoinListContract.View {

    override lateinit var presenter: CoinListContract.Presenter

    override fun getLayout() = R.layout.fragmnet_coin_list

    private val baseCurrency: String? by lazy {
        arguments?.getString(ARGUMENT_BASE_CURRENCY)
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

    override fun onResume() {
        super.onResume()
        presenter.start()
    }

    override fun onPause() {
        super.onPause()
        presenter.stop()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        CoinListPresenter(requireNotNull(baseCurrency), this)

        binding.rlTickerRefreshs.apply {
            scrollUpChild = binding.rvTickerList
            setOnRefreshListener {
                presenter.refreshTickers()
            }
        }

        binding.rvTickerList.adapter = TickerAdapter()
    }

    override fun showProgressBar(flag: Boolean) {
        with(binding.rlTickerRefreshs) {
            post { isRefreshing = flag }
        }
    }

    override fun updateList(list: List<Ticker>) {
        binding.it = list
    }

    override fun showToast(msg: String) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
    }
}