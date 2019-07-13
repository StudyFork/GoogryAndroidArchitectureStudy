package com.nanamare.mac.sample.ui.market

import android.os.Bundle
import android.view.View
import com.google.android.material.tabs.TabLayout
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.nanamare.mac.sample.R
import com.nanamare.mac.sample.adapter.MarketListViewPager
import com.nanamare.mac.sample.base.BaseFragment
import com.nanamare.mac.sample.databinding.FragmentMarketListViewpagerBinding
import com.nanamare.mac.sample.ui.coin.CoinFragment
import kotlinx.android.synthetic.main.fragment_market_list_viewpager.*


class MarketListFragment :
    BaseFragment<FragmentMarketListViewpagerBinding>(R.layout.fragment_market_list_viewpager) {

    private val marketListViewPager: MarketListViewPager by lazy {
        MarketListViewPager(
            childFragmentManager
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        val jsonMarketList = arguments?.getString(KET_MARKET_LIST)
        val type = object : TypeToken<HashMap<String, List<String>>>() {}.type
        val marketMap: HashMap<String, List<String>> = Gson().fromJson(jsonMarketList, type)
        marketMap.map {
            marketListViewPager.addFragment(CoinFragment.newInstance(it.value, it.key), it.key)
        }
        coin_view_pager.adapter = marketListViewPager
        val tabLayout = activity?.findViewById<TabLayout>(R.id.tl_market_list)
        tabLayout?.setupWithViewPager(coin_view_pager)
    }

    companion object {
        const val KET_MARKET_LIST = "KET_MARKET_LIST"
    }


}
