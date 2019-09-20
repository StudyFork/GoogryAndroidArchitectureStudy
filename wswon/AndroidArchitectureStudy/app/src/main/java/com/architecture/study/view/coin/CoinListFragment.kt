package com.architecture.study.view.coin

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.architecture.study.R
import com.architecture.study.data.repository.CoinRepositoryImpl
import com.architecture.study.databinding.FragmentCoinlistBinding
import com.architecture.study.util.Injection
import com.architecture.study.view.coin.adapter.CoinListAdapter
import com.architecture.study.viewmodel.CoinListViewModel

class CoinListFragment : Fragment(), CoinListAdapter.CoinItemRecyclerViewClickListener {

    private val coinListViewModel by lazy {
        CoinListViewModel(
            CoinRepositoryImpl.getInstance(Injection.provideCoinRemoteDataSource()),
            tabList.map { getString(it) }
        )
    }

    private var monetaryUnitNameList: List<String>? = null

    private lateinit var coinListAdapter: CoinListAdapter

    private lateinit var binding: FragmentCoinlistBinding

    private val tabList = listOf(
        R.string.monetary_unit_1,
        R.string.monetary_unit_2,
        R.string.monetary_unit_3,
        R.string.monetary_unit_4
    )

    private var refreshHandler = Handler()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_coinlist, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        /* 받아온 argument - Coin name */
        monetaryUnitNameList = arguments?.getStringArrayList(MONETARY_UNIT_NAME_LIST)

        monetaryUnitNameList?.let {
            getTickerList(it)
        }

        coinListAdapter = CoinListAdapter(this)

        binding.run {
            coinListVM = coinListViewModel
            recyclerViewCoinList.run {
                layoutManager = LinearLayoutManager(requireContext())
                adapter = coinListAdapter
            }
        }
    }

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

    /* handler로 5초간격 호출 재귀함수 */
    private fun refreshData() {
        refreshHandler = Handler().apply {
            postDelayed({
                monetaryUnitNameList?.let {
                    getTickerList(it)
                }
                refreshData()
            }, 5000)
        }
    }

    private fun getTickerList(marketNameList: List<String>){
        coinListViewModel.getTickerList(marketNameList){
            showMessage(it)
        }
    }

    private fun showMessage(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
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

        private const val MONETARY_UNIT_NAME_LIST = "MONETARY_UNIT_NAME_LIST"
    }
}