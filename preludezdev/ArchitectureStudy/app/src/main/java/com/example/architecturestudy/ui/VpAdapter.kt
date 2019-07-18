package com.example.architecturestudy.ui

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.PagerAdapter

class VpAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    private val fragmentTitleList = listOf("KRW", "BTC", "ETH", "USDT")

    override fun getItem(position: Int): Fragment? {
        return when (position) {
            0 -> KrwFragment()
            1 -> BtcFragment()
            2 -> EthFragment()
            3 -> UsdtFragment()
            else -> null
        }
    }

    //뷰페이저에 포함된 전체 Fragment 갯수
    override fun getCount(): Int {
        return fragmentTitleList.size
    }

    //탭 텍스트 설정
    override fun getPageTitle(position: Int): CharSequence? {
        return fragmentTitleList[position]
    }

    override fun getItemPosition(`object`: Any): Int {
        return PagerAdapter.POSITION_NONE
    }
}
