package org.study.kotlin.androidarchitecturestudy.adapter.viewpageradapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class MainViewPagerAdapter(manager: FragmentManager) :
    FragmentPagerAdapter(manager) {
    private val fragments: MutableList<Pair<Fragment, String>> =
        mutableListOf()

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

