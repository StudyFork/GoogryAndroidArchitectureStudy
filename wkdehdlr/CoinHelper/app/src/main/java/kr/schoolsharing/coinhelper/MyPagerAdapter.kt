package kr.schoolsharing.coinhelper

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

class MyPagerAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> FragmentKRW()
            1 -> FragmentBTC()
            2 -> FragmentETH()
            else -> return FragmentUSDT()
        }
    }

    override fun getCount(): Int = 4

    override fun getPageTitle(position: Int): CharSequence? {
        return when (position) {
            0 -> "KRW"
            1 -> "BTC"
            2 -> "ETH"
            else -> return "USDT"
        }
    }
}