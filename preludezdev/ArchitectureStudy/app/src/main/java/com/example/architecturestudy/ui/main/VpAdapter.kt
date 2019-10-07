package com.example.architecturestudy.ui.main

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.PagerAdapter
import com.example.architecturestudy.ui.market.MarketFragment

class VpAdapter(fm: FragmentManager) :
    FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    private val fragmentTitleList = listOf("KRW", "BTC", "ETH", "USDT")

    override fun getItem(position: Int): Fragment {
        return MarketFragment.newInstance(
            fragmentTitleList[position]
        )
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
