package com.example.mystudy.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.example.mystudy.ui.UpbitFragment

class ViewPagerAdapter(fm: FragmentManager, private val num_fragment: Int) :
    FragmentStatePagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getItem(p0: Int): Fragment {
        return when (p0) {
            0 -> UpbitFragment("KRW")
            1 -> UpbitFragment("BTC")
            2 -> UpbitFragment("ETH")
            3 -> UpbitFragment("USDT")
            else -> null
        }!!
    }

    override fun getCount() = num_fragment

}