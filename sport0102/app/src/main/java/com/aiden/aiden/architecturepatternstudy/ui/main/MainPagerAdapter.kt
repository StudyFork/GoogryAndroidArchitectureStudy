package com.aiden.aiden.architecturepatternstudy.ui.main

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.aiden.aiden.architecturepatternstudy.data.enums.Market

class MainPagerAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {

    private val marketList = Market.values()

    override fun getItem(position: Int): Fragment? {
        return when (position) {
            0 -> MainFragment(marketList[0])
            1 -> MainFragment(marketList[1])
            2 -> MainFragment(marketList[2])
            3 -> MainFragment(marketList[3])
            else -> null
        }

    }

    override fun getCount(): Int = marketList.size
}
