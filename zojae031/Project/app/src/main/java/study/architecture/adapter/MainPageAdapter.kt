package study.architecture.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import study.architecture.MainActivity
import study.architecture.MainActivity.Companion.TAB_NUMBER
import study.architecture.MainFragment

/**
 * [FragmentPagerAdapter] vs [FragmentStatePagerAdapter]
 * @see FragmentPagerAdapter
 * 등록한 Fragment를 최대한 영구적으로 가지도록 구현되어있음
 * 적은양의 Fragment를 사용할 때 효과적
 * @see FragmentStatePagerAdapter
 * Fragment를 사용하지 않을때 Destroy가 됨
 * 많은양의 Fragment를 사용할 때 효과적
 * -> [FragemntPagerAdapter]를 사용한 이유 : 정해진 갯수의 프래그먼트를 사용하며, Destroy가 되는경우 API 콜이 다시 일어나기 때문에 오버헤드를 줄이기 위함
 */
class MainPageAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {
    override fun getCount(): Int = TAB_NUMBER

    override fun getItem(position: Int): Fragment {
        when (position) {
            0 -> return MainFragment(MainActivity.Companion.TAB.KRW)
            1 -> return MainFragment(MainActivity.Companion.TAB.BTC)
            2 -> return MainFragment(MainActivity.Companion.TAB.ETH)
            3 -> return MainFragment(MainActivity.Companion.TAB.USDT)
        }
        return MainFragment(MainActivity.Companion.TAB.KRW)
    }

}