package com.example.architecturestudy.ui

import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.PagerAdapter
import com.example.architecturestudy.MarketFragment

class VpAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    private val fragmentTitleList = listOf("KRW", "BTC", "ETH", "USDT")

    override fun getItem(position: Int): Fragment? {
        return when (position) {
            0 -> MarketFragment.newInstance("KRW")
            1 -> MarketFragment.newInstance("BTC")
            2 -> MarketFragment.newInstance("ETH")
            3 -> MarketFragment.newInstance("USDT")

            else -> null
        }
    }

    //뷰페이저에 포함된 전체 Fragment 갯수
    override fun getCount(): Int {
        return fragmentTitleList.size
    }

    //탭 텍스트 제목 설정
    override fun getPageTitle(position: Int): CharSequence? {
        return fragmentTitleList[position]
    }

    //페이지 이동시 OnCreateView() 함수 호출해 뷰 다시 생성
    override fun getItemPosition(`object`: Any): Int {
        return PagerAdapter.POSITION_NONE
    }
}
