package com.test.androidarchitecture.ui.ticker


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.test.androidarchitecture.R
import com.test.androidarchitecture.adpter.TickerAdapter
import com.test.androidarchitecture.data.TickerFormat
import kotlinx.android.synthetic.main.fragment_coin.*


class TickerFragment :
    Fragment(),
    TickerContract.View {

    private val adapter by lazy { TickerAdapter() }
    private val presenter by lazy { TickerPresenter(this) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_coin, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val marketSearch = arguments?.getString(MARKET_SEARCH)
        coin_recyclerView.adapter = this.adapter
        marketSearch?.let { presenter.getTicker(it) }
    }

    override fun onDestroyView() {
        presenter.disposablesClear()
        super.onDestroyView()
    }

    override fun setTickerData(list: List<TickerFormat>) {
        adapter.setItem(list)
    }

    override fun showToast(msg: String) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
    }

    companion object {

        private const val MARKET_SEARCH: String = "marketSearch"

        fun getInstance(marketSearch: String): TickerFragment {
            val args = Bundle()
            args.putString(MARKET_SEARCH, marketSearch)
            val fragment = TickerFragment()
            fragment.arguments = args
            return fragment
        }
    }
}
