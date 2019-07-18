package com.architecture.study.fragment

import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.architecture.study.R
import com.architecture.study.activity.MainActivity
import com.architecture.study.adapter.CoinListAdapter
import kotlinx.android.synthetic.main.fragment_coinlist.*

class CoinListFragment : Fragment(), CoinListAdapter.CoinItemRecyclerViewClickListener {
    private lateinit var mCoinListAdapter: CoinListAdapter

    var monetaryUnitName: String? = null

    private var refreshHandler = Handler()


    /* 현재 보고있는 화면의 데이터만 갱신, 5초 간격 */
    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        if (isVisibleToUser) {
            refreshData()
        } else {
            refreshHandler.removeMessages(0)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_coinlist, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        /* 받아온 argument - Coin name */
        monetaryUnitName = arguments?.getString(MONETARY_UNIT_NAME)

        mCoinListAdapter = CoinListAdapter(requireContext()).apply {
            setOnClickListener(this@CoinListFragment)
            monetaryUnitName?.let {
                setMonetaryUnitName(it)
            }
        }

        setTickerData()

        coin_list_recyclerview.run {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = mCoinListAdapter
        }
    }

    /* handler로 5초간격 호출 재귀함수 */
    private fun refreshData() {
        refreshHandler = Handler().apply {
            postDelayed({
                (requireActivity() as MainActivity).refreshTickerData()
                Log.d("monetaryUnitName", monetaryUnitName)
                setTickerData()
                refreshData()
            }, 5000)
        }
    }


    /* adapter data setting */
    private fun setTickerData() {
        if ((requireActivity() as MainActivity).tickerList.isNotEmpty()) {
            val mCoinListData =
                (requireActivity() as MainActivity).tickerList.filter { it.market.split("-")[0] == monetaryUnitName }
            for (data in mCoinListData) {
                Log.d("market, acc_trade_price_24h", data.market + "," + data.acc_trade_price_24h)
            }
            mCoinListAdapter.setData(mCoinListData)
            mCoinListAdapter.notifyDataSetChanged()
        }
    }


    override fun onItemClicked(position: Int) {
        //click event
    }

    override fun onShareButtonClicked(position: Int) {
    }


    /* fragment singleton */
    companion object {
        fun newInstance(monetaryUnitName: String) = CoinListFragment().apply {
            arguments = Bundle().apply {
                putString(MONETARY_UNIT_NAME, monetaryUnitName)
            }
        }
        const val MONETARY_UNIT_NAME = "monetaryUnitName"
    }

}