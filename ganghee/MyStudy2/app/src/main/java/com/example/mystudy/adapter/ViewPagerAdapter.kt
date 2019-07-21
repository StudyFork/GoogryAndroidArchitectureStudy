package com.example.mystudy.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.example.mystudy.fragment.UpbitFragment

class ViewPagerAdapter(fm: FragmentManager, private val num_fragment: Int) : FragmentStatePagerAdapter(fm) {

/*    //Singleton Design Pattern: 기존에 생성되었던 객체를 재사용
    companion object {
        private var upbitFragment: UpbitFragment? = null
    }

    @Synchronized
    fun getUpbitFragment(): UpbitFragment {
        if (upbitFragment == null) upbitFragment = UpbitFragment()
        return upbitFragment!!
    }*/

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