package kr.schoolsharing.coinhelper.util

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import kr.schoolsharing.coinhelper.model.UpbitList
import kr.schoolsharing.coinhelper.ui.FragmentBTC
import kr.schoolsharing.coinhelper.ui.FragmentETH
import kr.schoolsharing.coinhelper.ui.FragmentKRW
import kr.schoolsharing.coinhelper.ui.FragmentUSDT

class MyPagerAdapter(fm: FragmentManager, upbitList: UpbitList) : FragmentStatePagerAdapter(fm) {

    private val upbitList = upbitList
    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> FragmentKRW(upbitList.krwList)
            1 -> FragmentBTC(upbitList.btcList)
            2 -> FragmentETH(upbitList.ethList)
            else -> return FragmentUSDT(upbitList.usdtList)
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