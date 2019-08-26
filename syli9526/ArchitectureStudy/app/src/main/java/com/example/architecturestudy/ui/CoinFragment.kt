package com.example.architecturestudy.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.architecturestudy.R
import com.example.architecturestudy.data.model.Ticker
import com.example.architecturestudy.data.source.CoinRepository
import com.example.architecturestudy.ui.adapter.CoinAdapter
import com.example.architecturestudy.data.source.UpbitListener
import kotlinx.android.synthetic.main.fragment_list_coin.*

class CoinFragment : Fragment() {


    private val coinAdapter by lazy { CoinAdapter() }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_list_coin, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val currencyList = arguments?.getString(CURRENCY_LIST) ?: ""
        getTickerInfo(currencyList)

        setCoinAdapter()

    }

    private fun setCoinAdapter() {

        rv_coin_list.run {
            addItemDecoration(DividerItemDecoration(context, 1))
            layoutManager = LinearLayoutManager(requireContext())
            adapter = coinAdapter
        }

    }

    private fun getTickerInfo(name: String) {

        CoinRepository.getInstance().getTickerInfo(name, object : UpbitListener<Ticker> {

            override fun onResponse(dataList: List<Ticker>) {
                coinAdapter.setData(dataList)
            }

            override fun onFailure(str: String) {

            }

        })
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