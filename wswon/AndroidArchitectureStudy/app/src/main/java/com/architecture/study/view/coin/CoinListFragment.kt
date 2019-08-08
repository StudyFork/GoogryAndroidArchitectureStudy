package com.architecture.study.view.coin

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.architecture.study.R
import com.architecture.study.view.coin.adapter.CoinListAdapter
import com.architecture.study.data.model.Ticker
import com.architecture.study.data.repository.CoinRepositoryImp
import com.architecture.study.network.model.TickerResponse
import com.architecture.study.view.coin.presenter.CoinListFragmentContract
import com.architecture.study.view.coin.presenter.CoinListFragmentPresenter
import kotlinx.android.synthetic.main.fragment_coinlist.*

class CoinListFragment : Fragment(), CoinListFragmentContract.View, CoinListAdapter.CoinItemRecyclerViewClickListener {
    override lateinit var presenter: CoinListFragmentContract.Presenter
    private lateinit var coinListAdapter: CoinListAdapter

    private var monetaryUnitNameList: List<String>? = null
    private lateinit var tickerList: List<Ticker>

    private var refreshHandler = Handler()
    override var isActive = false
        get() = isAdded


    /* 현재 보고있는 화면의 데이터만 갱신, 5초 간격 */
    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        if (isVisibleToUser) {
            refreshData()
        } else {
            refreshHandler.removeMessages(0)
        }
    }

    override fun onResume() {
        super.onResume()
        presenter.start()
    }

    override fun onPause() {
        super.onPause()
        refreshHandler.removeMessages(0)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_coinlist, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        context?.let {
            presenter = CoinListFragmentPresenter(
                CoinRepositoryImp.getInstance(),
                this@CoinListFragment,
                it,
                false
            )
        }


        /* 받아온 argument - Coin name */
        monetaryUnitNameList = arguments?.getStringArrayList(MONETARY_UNIT_NAME_LIST)

        coinListAdapter = CoinListAdapter(requireContext(), this)

        coin_list_recyclerview.run {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = coinListAdapter
        }

    }



    /* TickerResponse -> Ticker로 변환작업 */
    override fun showTickerList(tickerList: List<Ticker>) {

        coinListAdapter.setData(tickerList)
    }

    override fun showEmptyTickerData(empty: String) {
        Toast.makeText(requireContext(), empty, Toast.LENGTH_SHORT).show()
    }

    override fun showFailureGetTickerData(failure: String) {
        Toast.makeText(requireContext(), failure, Toast.LENGTH_SHORT).show()
    }

    override fun successConnectApi() {
        monetaryUnitNameList?.let {
            presenter.getTickerList(it.joinToString(","))
        }
    }

    override fun showFailedConnectError() {
        Toast.makeText(requireContext(), getString(R.string.failed_connect_api), Toast.LENGTH_SHORT).show()
    }

    /* handler로 5초간격 호출 재귀함수 */
    private fun refreshData() {
        refreshHandler = Handler().apply {
            postDelayed({
                monetaryUnitNameList?.let {
                    presenter.getTickerList(it.joinToString(","))
                }
                refreshData()
            }, 5000)
        }
    }

    override fun onItemClicked(position: Int) {
        //click event
    }


    companion object {
        fun newInstance(monetaryUnitNameList: ArrayList<String>?) = CoinListFragment().apply {
            arguments = Bundle().apply {
                monetaryUnitNameList?.let {
                    putStringArrayList(MONETARY_UNIT_NAME_LIST, it)
                }
            }
        }

        private const val MONETARY_UNIT_NAME_LIST = "MONETARY_UNIT_NAME"
    }
}