package kr.schoolsharing.coinhelper.tasks

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class PagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    private val marketList = listOf("KRW", "BTC", "ETH", "USDT")

    override fun getItem(position: Int): Fragment = UpbitFragment.newInstance(marketList[position])

    override fun getCount(): Int = 4

    override fun getPageTitle(position: Int): CharSequence? = marketList[position]

}