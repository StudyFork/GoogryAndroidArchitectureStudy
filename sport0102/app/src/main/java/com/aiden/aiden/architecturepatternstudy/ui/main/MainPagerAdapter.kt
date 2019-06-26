package com.aiden.aiden.architecturepatternstudy.ui.main

import android.os.Bundle
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.aiden.aiden.architecturepatternstudy.data.enums.Market


class MainPagerAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    private val marketList = Market.values()

    override fun getItem(position: Int) = when (position) {
        0 -> getFragmentByMarketName(Market.KRW.marketName)
        1 -> getFragmentByMarketName(Market.BTC.marketName)
        2 -> getFragmentByMarketName(Market.ETH.marketName)
        else -> getFragmentByMarketName(Market.USDT.marketName)
    }

    private fun getFragmentByMarketName(marketName: String): MainFragment {
        val mainFragment = MainFragment()
        mainFragment.arguments = Bundle().apply {
            putString("marketName", marketName)
        }
        return mainFragment
    }

    override fun getCount() = marketList.size
}
