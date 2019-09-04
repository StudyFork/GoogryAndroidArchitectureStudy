package com.example.architecturestudy.ui.coin

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.architecturestudy.R
import com.example.architecturestudy.data.model.Ticker
import com.example.architecturestudy.ui.adapter.CoinAdapter
import kotlinx.android.synthetic.main.fragment_list_coin.*

class CoinFragment : Fragment(), CoinContract.View {

    private val coinAdapter by lazy { CoinAdapter() }
    private val presenter by lazy { CoinPresenter(this) }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_list_coin, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val currencyList = arguments?.getString(CURRENCY_LIST) ?: ""
        presenter.getTickerList(currencyList)

        setCoinAdapter()

    }

    override fun setData(tickerList: List<Ticker>) {
        coinAdapter.setData(tickerList)
    }

    override fun showMessage(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    private fun setCoinAdapter() {

        rv_coin_list.run {
            addItemDecoration(DividerItemDecoration(context, 1))
            layoutManager = LinearLayoutManager(requireContext())
            adapter = coinAdapter
        }

    }

    companion object {

        private const val CURRENCY_LIST = "currency_list"

        fun newInstance(currencyList: String) = CoinFragment().apply {
            arguments = Bundle().apply {
                putString(CURRENCY_LIST, currencyList)
            }
        }
    }

}