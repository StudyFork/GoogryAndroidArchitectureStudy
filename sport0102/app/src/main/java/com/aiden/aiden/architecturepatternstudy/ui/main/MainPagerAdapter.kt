package com.aiden.aiden.architecturepatternstudy.ui.main

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.aiden.aiden.architecturepatternstudy.data.enums.Market

class MainPagerAdapter(fm: FragmentManager, val fragment: ArrayList<Fragment>) : FragmentStatePagerAdapter(fm) {

    private val marketList = Market.values()

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> fragment[0]
            1 -> fragment[1]
            2 -> fragment[2]
            3 -> fragment[3]
            else -> fragment[0]
        }

    }

    override fun getCount(): Int = marketList.size
}
