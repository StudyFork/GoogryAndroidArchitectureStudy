package com.nanamare.mac.sample.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.ViewCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.malinskiy.superrecyclerview.OnMoreListener
import com.nanamare.mac.sample.R
import com.nanamare.mac.sample.adapter.TickerAdapter
import com.nanamare.mac.sample.api.DisposableManager
import com.nanamare.mac.sample.api.upbit.TickerModel
import com.nanamare.mac.sample.api.upbit.UpBitServiceManager
import io.reactivex.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.fragment_coin_list.*

class CoinFragment : Fragment(), SwipeRefreshLayout.OnRefreshListener, OnMoreListener {

    private lateinit var ticketList: MutableList<String>

    private lateinit var disposableManager: DisposableManager

    private lateinit var adapter: TickerAdapter

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

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_coin_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loadBundleData(savedInstanceState)

        initView()

        onRefresh()
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
        adapter = TickerAdapter(listOf())
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
        disposableManager = DisposableManager
        disposableManager.add(
            UpBitServiceManager.getTickerList(ticketList)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    setData(it.body())
                }, {
                    println(it.toString())
                })
        )
    }

    private fun setData(list: List<TickerModel>?) {
        if (adapter.itemCount > 0) {
            adapter.clear()
        }
        adapter.addAll(list)
    }

    override fun onMoreAsked(overallItemsCount: Int, itemsBeforeMore: Int, maxLastVisiblePosition: Int) {
        if (maxLastVisiblePosition == rv_coin_list.adapter.itemCount - 1) {
            Toast.makeText(context, getString(R.string.scroll_end), Toast.LENGTH_SHORT).show()
            rv_coin_list.hideMoreProgress()

        }
    }


    override fun onDestroy() {
        disposableManager.dispose()
        super.onDestroy()
    }
}