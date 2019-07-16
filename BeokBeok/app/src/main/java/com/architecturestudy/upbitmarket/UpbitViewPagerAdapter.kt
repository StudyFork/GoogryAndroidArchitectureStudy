package com.architecturestudy.upbitmarket

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.architecturestudy.data.common.MarketTypes

class UpbitViewPagerAdapter(
    fm: FragmentManager
) : FragmentStatePagerAdapter(fm) {

    override fun getCount(): Int = MarketTypes.values().size

    override fun getItem(position: Int): Fragment = UpbitContentsFragment(position)
}