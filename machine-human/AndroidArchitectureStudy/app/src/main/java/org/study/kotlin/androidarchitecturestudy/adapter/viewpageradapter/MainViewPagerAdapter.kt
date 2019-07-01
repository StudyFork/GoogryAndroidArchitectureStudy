package org.study.kotlin.androidarchitecturestudy.adapter.viewpageradapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

class MainViewPagerAdapter(manager: FragmentManager) : FragmentPagerAdapter(manager) {
    private val fragments: MutableList<Pair<Fragment, String>> = mutableListOf()
    override fun getItem(position: Int): Fragment {
        return fragments[position].first
    }

    override fun getCount(): Int {
        return fragments.size
    }

    fun addFragment(fragment: Fragment, title: String) {
        fragments?.add(Pair(fragment, title))

    }

    override fun getPageTitle(position: Int): CharSequence? {
        return fragments[position].second
    }
}

