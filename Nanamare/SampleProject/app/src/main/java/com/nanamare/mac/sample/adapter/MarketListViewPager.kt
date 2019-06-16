package com.nanamare.mac.sample.adapter


import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import java.util.*


class MarketListViewPager(fm: FragmentManager) : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    private val mFragments = ArrayList<Fragment>()
    private val mFragmentTiles = ArrayList<String>()


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