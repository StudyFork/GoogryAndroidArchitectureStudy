package com.architecture.study.view.coin

import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.architecture.study.R
import com.architecture.study.view.coin.adapter.CoinListAdapter
import com.architecture.study.data.model.Ticker
import com.architecture.study.data.source.CoinRepository
import com.architecture.study.network.model.TickerResponse
import com.architecture.study.data.source.CoinRemoteDataSourceListener
import kotlinx.android.synthetic.main.fragment_coinlist.*

class CoinListFragment : Fragment(), CoinListAdapter.CoinItemRecyclerViewClickListener {
    private lateinit var coinListAdapter: CoinListAdapter

    private var monetaryUnitNameList: List<String>? = null
    private lateinit var tickerList: List<Ticker>

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

    override fun onPause() {
        super.onPause()
        refreshHandler.removeMessages(0)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_coinlist, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


        /* 받아온 argument - Coin name */
        monetaryUnitNameList = arguments?.getStringArrayList(MONETARY_UNIT_NAME_LIST)

        monetaryUnitNameList?.let {
            getTickerList(it.joinToString(","))
        }


        coinListAdapter = CoinListAdapter(requireContext(), this)

        coin_list_recyclerview.run {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = coinListAdapter
        }
    }

    /* handler로 5초간격 호출 재귀함수 */
    private fun refreshData() {
        refreshHandler = Handler().apply {
            postDelayed({
                monetaryUnitNameList?.let {
                    Log.d("1111", it[0])
                    getTickerList(it.joinToString(","))
                }
                refreshData()
            }, 5000)
        }
    }


    /* retrofit getTickerList */
    private fun getTickerList(marketNames: String) {
       CoinRepository.getInstance().getTickerList(marketNames, object :
           CoinRemoteDataSourceListener<TickerResponse> {
           override fun onSucess(dataList: List<TickerResponse>) {
               val _tickerList = mutableListOf<Ticker>()
               dataList.forEach {
                   _tickerList.add(
                        it.toTicker(requireContext())
                   )
               }
               tickerList = _tickerList
               coinListAdapter.setData(tickerList)
           }

           override fun onEmpty(str: String) {
               Toast.makeText(requireContext(), "data Empty : $str", Toast.LENGTH_LONG).show()
           }

           override fun onFailure(str: String) {
               Toast.makeText(requireContext(), "call failure : $str", Toast.LENGTH_LONG).show()
           }
       })
    }

    override fun onItemClicked(position: Int) {
        //click event
    }

    /* fragment singleton */
    companion object {
        fun newInstance(monetaryUnitNameList: ArrayList<String>) = CoinListFragment().apply {
            arguments = Bundle().apply {
                putStringArrayList(MONETARY_UNIT_NAME_LIST, monetaryUnitNameList)
            }
        }

        private const val MONETARY_UNIT_NAME_LIST = "MONETARY_UNIT_NAME"
    }
}