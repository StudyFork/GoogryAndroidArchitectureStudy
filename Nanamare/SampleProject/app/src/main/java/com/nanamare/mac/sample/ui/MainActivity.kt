package com.nanamare.mac.sample.ui

import android.os.Bundle
import com.google.gson.Gson
import com.nanamare.mac.sample.R
import com.nanamare.mac.sample.base.BaseActivity
import com.nanamare.mac.sample.databinding.ActivityMainBinding
import com.nanamare.mac.sample.ui.market.MarketListFragment
import com.nanamare.mac.sample.ui.market.MarketNavigator
import com.nanamare.mac.sample.vm.MarketViewModel

class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main), MarketNavigator {

    private val marketVM: MarketViewModel by lazy { MarketViewModel() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        with(marketVM) {
            navigator = this@MainActivity
            onMarketClick()
        }

    }

    override fun sendMarketList(marketMap: LinkedHashMap<String, List<String>>) {
        val bundle = Bundle().apply {
            putString(KET_MARKET_LIST, Gson().toJson(marketMap))
        }
        goToFragment(MarketListFragment::class.java, bundle)
    }

    override fun onDestroy() {
        marketVM.onClose()
        super.onDestroy()
    }

}
