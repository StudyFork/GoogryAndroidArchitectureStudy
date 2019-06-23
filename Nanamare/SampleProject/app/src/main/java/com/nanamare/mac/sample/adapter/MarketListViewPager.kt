package com.nanamare.mac.sample.adapter


import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter


class MarketListViewPager(fm: FragmentManager) : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    private val fragmentList : MutableList<Pair<Fragment, String>> = mutableListOf()

    fun addFragment(fragment: Fragment, title: String) {
        fragmentList.add(fragment to title)
    }

    override fun getItem(position: Int): Fragment {
        return fragmentList[position].first
    }

    override fun getCount(): Int {
        return fragmentList.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return fragmentList[position].second
    }

}