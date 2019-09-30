package com.test.androidarchitecture.ui.ticker


import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.test.androidarchitecture.R
import com.test.androidarchitecture.adpter.TickerAdapter
import com.test.androidarchitecture.base.BaseFragment
import com.test.androidarchitecture.data.TickerFormat
import kotlinx.android.synthetic.main.fragment_coin.*


class TickerFragment :
    BaseFragment(R.layout.fragment_coin),
    TickerContract.View {

    private val adapter by lazy { TickerAdapter() }
    override val presenter by lazy { TickerPresenter(this, marketSearch) }
    private val marketSearch by lazy { arguments!!.getString(MARKET_SEARCH) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        start()
        presenter.getTicker()
    }

    override fun onDestroyView() {
        presenter.clearDisposable()
        super.onDestroyView()
    }

    private fun start() {
        coin_recyclerView.adapter = this.adapter
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
