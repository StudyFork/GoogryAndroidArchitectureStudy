package com.nanamare.mac.sample.ui

import android.os.Bundle
import com.nanamare.mac.sample.R
import com.nanamare.mac.sample.base.BaseActivity
import com.nanamare.mac.sample.ui.market.MarketContract
import com.nanamare.mac.sample.ui.market.MarketPresenter

class MainActivity : BaseActivity(), MarketContract.MarketView {

    private lateinit var presenter: MarketPresenter

    companion object {
        const val KET_MARKET_LIST = "key_market_list"
        const val PROGRESS_DIALOG_FRAGMENT = "progress_dialog_fragment"
    }

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


}