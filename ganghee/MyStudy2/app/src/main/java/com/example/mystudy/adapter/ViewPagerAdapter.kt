package com.example.mystudy.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.fragment.app.FragmentStatePagerAdapter
import com.example.mystudy.ui.UpbitFragment

class ViewPagerAdapter(fm: FragmentManager) :
    FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    private val marketList = listOf("KRW", "BTC", "ETH", "USDT")

    override fun getItem(position: Int): Fragment = UpbitFragment.newInstance(marketList[position])

    override fun getCount() = marketList.size

    override fun getPageTitle(position: Int): CharSequence? {
        return marketList[position]
    }
}