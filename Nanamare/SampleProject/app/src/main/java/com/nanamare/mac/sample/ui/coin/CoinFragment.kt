package com.nanamare.mac.sample.ui.coin

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.malinskiy.superrecyclerview.OnMoreListener
import com.nanamare.mac.sample.R
import com.nanamare.mac.sample.adapter.TickerAdapter
import com.nanamare.mac.sample.api.DisposableManager
import com.nanamare.mac.sample.base.BaseFragment
import com.nanamare.mac.sample.databinding.FragmentCoinListBinding
import com.nanamare.mac.sample.vm.CoinViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.fragment_coin_list.*

class CoinFragment : BaseFragment<FragmentCoinListBinding>(R.layout.fragment_coin_list),
    SwipeRefreshLayout.OnRefreshListener, OnMoreListener {

    private lateinit var ticketList: MutableList<String>

    private val adapter: TickerAdapter by lazy { TickerAdapter() }

    private val coinVM: CoinViewModel by lazy { CoinViewModel() }

    private val disposableManager = DisposableManager()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loadBundleData(savedInstanceState)

        initView()

        with(coinVM) {
            showLoadingDialog()
            getCoins(ticketList)
            disposableManager.add(
                coinsObservable.observeOn(AndroidSchedulers.mainThread())
                    .subscribe({
                        hideLoadingDialog()
                    }, {
                        hideLoadingDialog()
                    })
            )
        }

    }

    private fun initView() {
        binding.run {
            coinViewModel = coinVM
            rvCoinList.setLayoutManager(LinearLayoutManager(context))
            rvCoinList.setRefreshListener(this@CoinFragment)
            rvCoinList.setRefreshingColorResources(
                R.color.point_424C57,
                R.color.point_5FA9D0,
                R.color.white_87,
                R.color.point_E47B75
            )
            rvCoinList.setupMoreListener(this@CoinFragment, 1)
            rvCoinList.adapter = adapter

        }
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
        if (maxLastVisiblePosition == adapter.itemCount - 1) {
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
        coinVM.close()
        disposableManager.dispose()
        super.onDestroyView()
    }
}