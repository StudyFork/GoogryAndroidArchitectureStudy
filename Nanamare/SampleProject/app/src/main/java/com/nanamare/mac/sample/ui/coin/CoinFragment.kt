package com.nanamare.mac.sample.ui.coin

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.malinskiy.superrecyclerview.OnMoreListener
import com.nanamare.mac.sample.R
import com.nanamare.mac.sample.adapter.TickerAdapter
import com.nanamare.mac.sample.base.BaseFragment
import com.nanamare.mac.sample.databinding.FragmentCoinListBinding
import com.nanamare.mac.sample.vm.CoinViewModel
import kotlinx.android.synthetic.main.fragment_coin_list.*

class CoinFragment : BaseFragment<FragmentCoinListBinding>(R.layout.fragment_coin_list),
    SwipeRefreshLayout.OnRefreshListener, OnMoreListener,
    CoinNavigator {

    private lateinit var ticketList: MutableList<String>

    private lateinit var adapter: TickerAdapter

    private lateinit var coinVM: CoinViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loadBundleData(savedInstanceState)

        initView()

        coinVM = CoinViewModel()
        coinVM.getCoins(ticketList)
        coinVM.navigator = this@CoinFragment

        binding.run {
            coinViewModel = coinVM
        }

    }

    private fun initView() {
        rv_coin_list.setLayoutManager(LinearLayoutManager(context))
        rv_coin_list.setRefreshListener(this)
        rv_coin_list.setRefreshingColorResources(
            R.color.point_424C57,
            R.color.point_5FA9D0,
            R.color.white_87,
            R.color.point_E47B75
        )
        rv_coin_list.setupMoreListener(this, 1)
        adapter = TickerAdapter()
        rv_coin_list.adapter = adapter
        ViewCompat.setNestedScrollingEnabled(rv_coin_list, true)
    }

    private fun loadBundleData(savedInstanceState: Bundle?) {
        val bundle = savedInstanceState ?: arguments ?: return

        val list = bundle.getStringArrayList(KEY_COIN_TITLE)
        val filter = bundle.getString(KEY_FILTER)

        ticketList = mutableListOf()
        (list as ArrayList<String>).map { coin ->
            coin.split("-")[0].let {
                if (it.contains(filter!!)) {
                    ticketList.add(coin)
                }
            }
        }
    }

    override fun onRefresh() {
        coinVM.getCoins(ticketList)
    }

    override fun onMoreAsked(
        overallItemsCount: Int,
        itemsBeforeMore: Int,
        maxLastVisiblePosition: Int
    ) {
        if (maxLastVisiblePosition == rv_coin_list.adapter.itemCount - 1) {
            Toast.makeText(context, getString(R.string.scroll_end), Toast.LENGTH_SHORT).show()
            rv_coin_list.hideMoreProgress()
        }
    }

    companion object {
        private const val KEY_COIN_TITLE = "key_coin_title"
        private const val KEY_FILTER = "key_filter"

        fun newInstance(marketList: List<String>, key: String): CoinFragment {
            val args = Bundle()
            args.putStringArrayList(KEY_COIN_TITLE, ArrayList(marketList))
            args.putString(KEY_FILTER, key)
            val fragment = CoinFragment()
            fragment.arguments = args
            return fragment
        }

    }

    override fun onDestroyView() {
        coinVM.onClose()
        super.onDestroyView()
    }
}