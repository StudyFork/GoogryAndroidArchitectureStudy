package com.nanamare.mac.sample.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayout
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.nanamare.mac.sample.R
import com.nanamare.mac.sample.adapter.MarketListViewPager
import kotlinx.android.synthetic.main.fragment_market_list_viewpager.*


class MarketListFragment : Fragment() {

    companion object {
        const val KET_MARKET_LIST = "key_market_list"
    }

    private lateinit var marketListViewPager: MarketListViewPager

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_market_list_viewpager, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        marketListViewPager = MarketListViewPager(childFragmentManager)

        val jsonMarketList = arguments?.getString(KET_MARKET_LIST)
        val type = object : TypeToken<HashMap<String, List<String>>>() {}.type
        val marketMap: HashMap<String, List<String>> = Gson().fromJson(jsonMarketList, type)

        val itr = marketMap.iterator()
        while (itr.hasNext()) {
            val item = itr.next()
            marketListViewPager.addFragment(CoinFragment.newInstance(item.value, item.key), item.key)
        }

        coin_view_pager.adapter = marketListViewPager

        val tabLayout = activity?.findViewById<TabLayout>(R.id.tl_market_list)
        tabLayout?.setupWithViewPager(coin_view_pager)

    }


}
