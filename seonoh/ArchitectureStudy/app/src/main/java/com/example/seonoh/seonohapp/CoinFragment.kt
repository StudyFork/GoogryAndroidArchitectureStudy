package com.example.seonoh.seonohapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.seonoh.seonohapp.contract.CoinFragmentContract
import com.example.seonoh.seonohapp.model.UseCoinModel
import kotlinx.android.synthetic.main.coin_fragment.*


class CoinFragment : Fragment(), CoinFragmentContract.View {

    private lateinit var mAdapter: CoinAdapter
    private var marketName: String? = null
    private val presenter by lazy { CoinPresenter(this) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.coin_fragment, container, false)
    }

    override fun initRecyclerView(data: List<UseCoinModel>) {
        mAdapter.addCoinData(data)
    }

    private fun initView() {
        mAdapter = CoinAdapter()
        krwRecyclerView.apply {
            adapter = mAdapter
        }
    }

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

    override fun onDestroyView() {
        presenter.clearDisposable()
        super.onDestroyView()
    }

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
