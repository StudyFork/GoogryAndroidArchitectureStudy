package com.example.architecturestudy.ui

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class MainAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    val fragmentTitleList = listOf("KRW", "BTC", "ETH", "USDT")

    override fun getItem(position: Int): Fragment? {
        return when (position) {
            0 -> KRWFragment()
            1 -> BTCFragment()
            2 -> ETHFragment()
            3 -> USDTFragment()
            else -> null
        }

    }

    //생성할 Fragment 갯수
    override fun getCount(): Int {
        return 4
    }

    //탭 텍스트 설정
    override fun getPageTitle(position: Int): CharSequence? {
        return fragmentTitleList[position]
    }
}
