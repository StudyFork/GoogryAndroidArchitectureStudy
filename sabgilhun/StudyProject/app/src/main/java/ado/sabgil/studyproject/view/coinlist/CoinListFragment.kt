package ado.sabgil.studyproject.view.coinlist

import ado.sabgil.studyproject.data.model.Ticker
import ado.sabgil.studyproject.enums.BaseCurrency
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment

class CoinListFragment : Fragment(), CoinListContract.View {

    override lateinit var presenter: CoinListContract.Presenter

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
    }


    override fun showProgressBar(flag: Boolean) {
    }

    override fun updateList(list: List<Ticker>) {
    }


}