package com.nanamare.mac.sample.ui

import android.os.Bundle
import com.google.gson.Gson
import com.nanamare.mac.sample.R
import com.nanamare.mac.sample.base.BaseActivity
import com.nanamare.mac.sample.ui.market.MarketContract
import com.nanamare.mac.sample.ui.market.MarketListFragment
import com.nanamare.mac.sample.ui.market.MarketPresenter

class MainActivity : BaseActivity(), MarketContract.MarketView {
    private lateinit var presenter: MarketPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        presenter = MarketPresenter(this)
        presenter.getMarketList()
    }


    override fun onDestroy() {
        presenter.close()
        super.onDestroy()
    }

    override fun showMarketList(marketMap: LinkedHashMap<String, List<String>>) {
        val bundle = Bundle().apply {
            putString(KET_MARKET_LIST, Gson().toJson(marketMap))
        }
        goToFragment(MarketListFragment::class.java, bundle)
    }


}