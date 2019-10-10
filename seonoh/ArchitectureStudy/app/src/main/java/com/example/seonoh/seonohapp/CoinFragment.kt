package com.example.seonoh.seonohapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.seonoh.seonohapp.contract.BaseContract
import com.example.seonoh.seonohapp.contract.CoinFragmentContract
import com.example.seonoh.seonohapp.contract.CoinMainContract
import com.example.seonoh.seonohapp.model.UseCoinModel
import kotlinx.android.synthetic.main.coin_fragment.*

class CoinFragment : BaseFragment<CoinFragmentContract.Presenter>(
    R.layout.coin_fragment
), CoinFragmentContract.View {

    override val presenter = CoinPresenter(this)
    private lateinit var fragmentAdapter: CoinAdapter
    private var marketName: String? = null

    override fun setData(data: List<UseCoinModel>) = fragmentAdapter.addCoinData(data)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initView()
        marketName = arguments?.getString(MARKET)
        marketName?.let {
            presenter.loadData(it)
        } ?: Toast.makeText(
            activity,
            resources.getString(R.string.empty_market_text),
            Toast.LENGTH_LONG
        ).show()
    }

    private fun initView() {
        fragmentAdapter = CoinAdapter()
        krwRecyclerView.adapter = fragmentAdapter
    }

    override fun showToast(){}

    companion object {
        private const val MARKET = "market"

        fun newInstance(data: String): CoinFragment {
            val fragment = CoinFragment()
            val bundle = Bundle()
            bundle.putString(MARKET, data)
            fragment.arguments = bundle
            return fragment
        }
    }
}
