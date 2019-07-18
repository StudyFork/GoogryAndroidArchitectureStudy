package kr.schoolsharing.coinhelper

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

class MyPagerAdapter(fm: FragmentManager, val apiConnector: ApiConnector) : FragmentStatePagerAdapter(fm) {


    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> FragmentKRW(apiConnector.krwList)
            1 -> FragmentBTC(apiConnector.btcList)
            2 -> FragmentETH(apiConnector.ethList)
            else -> return FragmentUSDT(apiConnector.usdtList)
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