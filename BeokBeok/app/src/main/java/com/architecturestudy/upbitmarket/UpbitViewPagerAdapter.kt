package com.architecturestudy.upbitmarket

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.architecturestudy.data.common.MarketTypes

class UpbitViewPagerAdapter(
    fm: FragmentManager
) : FragmentStatePagerAdapter(fm) {

    override fun getCount(): Int = MarketTypes.values().size

    override fun getItem(position: Int): Fragment = when (position) {
        1 -> getFragmentInstance(MarketTypes.BTC.name)
        2 -> getFragmentInstance(MarketTypes.ETH.name)
        3 -> getFragmentInstance(MarketTypes.USDT.name)
        else -> getFragmentInstance(MarketTypes.KRW.name)
    }

    private fun getFragmentInstance(marketType: String): UpbitContentsFragment {
        val upbitContentsFragment = UpbitContentsFragment()
        upbitContentsFragment.arguments = Bundle().apply {
            putString("marketType", marketType)
        }
        return upbitContentsFragment
    }
}