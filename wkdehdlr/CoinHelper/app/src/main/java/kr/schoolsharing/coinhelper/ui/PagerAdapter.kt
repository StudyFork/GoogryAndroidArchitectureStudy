package kr.schoolsharing.coinhelper.ui

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

class PagerAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {

    private val marketList = listOf("KRW", "BTC", "ETH", "USDT")

    override fun getItem(position: Int): Fragment = CoinFragment(marketList[position])

    override fun getCount(): Int = 4

    override fun getPageTitle(position: Int): CharSequence? = marketList[position]

}