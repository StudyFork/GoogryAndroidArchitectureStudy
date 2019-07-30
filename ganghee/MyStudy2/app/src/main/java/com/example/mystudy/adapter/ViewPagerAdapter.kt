package com.example.mystudy.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.example.mystudy.ui.fragment.UpbitFragment

class ViewPagerAdapter(fm: FragmentManager, private val num_fragment: Int) : FragmentStatePagerAdapter(fm) {

    override fun getItem(p0: Int): Fragment {
        return when (p0) {
            0 -> UpbitFragment("KRW")
            1 -> UpbitFragment("BTC")
            2 -> UpbitFragment("ETH")
            3 -> UpbitFragment("USDT")
            else -> null
        }!!
    }

    override fun getCount(): Int {
        return num_fragment
    }
}