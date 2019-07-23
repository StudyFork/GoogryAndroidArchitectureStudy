package study.architecture.ui.adapter

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import study.architecture.ui.MainFragment

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
    private val fragments = MainFragment.FragIndex.values().map { idx ->
        MainFragment().apply {
            arguments = Bundle().apply {
                putSerializable("idx", idx)
            }
        }
    }

    override fun getCount(): Int = fragments.size


    override fun getItem(position: Int): Fragment =
        fragments[position]


}