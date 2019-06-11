package com.nanamare.mac.sample.adapter


import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.fragment.app.FragmentStatePagerAdapter
import java.util.ArrayList

/**
 * 생성자
 * @param fm
 */
class MarketListViewPager
    (fm: FragmentManager) : FragmentPagerAdapter(fm) {
    private val mFragments = ArrayList<Fragment>()
    private val mFragmentTiles = ArrayList<String>()

    /**
     * 추가
     * @param fragment
     * @param title
     */
    fun addFragment(fragment: Fragment, title: String) {
        mFragments.add(fragment)
        mFragmentTiles.add(title)
    }

    override fun getItem(position: Int): Fragment {
        return mFragments[position]
    }

    override fun getCount(): Int {
        return mFragments.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return mFragmentTiles[position]
    }

}