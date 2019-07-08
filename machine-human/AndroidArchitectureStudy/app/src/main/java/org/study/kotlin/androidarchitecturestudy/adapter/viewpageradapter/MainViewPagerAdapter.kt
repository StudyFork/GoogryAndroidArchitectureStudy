package org.study.kotlin.androidarchitecturestudy.adapter.viewpageradapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class MainViewPagerAdapter(manager: androidx.fragment.app.FragmentManager) : androidx.fragment.app.FragmentPagerAdapter(manager) {
    private val fragments: MutableList<Pair<androidx.fragment.app.Fragment, String>> = mutableListOf()
    override fun getItem(position: Int): androidx.fragment.app.Fragment {
        return fragments[position].first
    }

    override fun getCount(): Int {
        return fragments.size
    }

    fun addFragment(fragment: androidx.fragment.app.Fragment, title: String) {
        fragments?.add(Pair(fragment, title))

    }

    override fun getPageTitle(position: Int): CharSequence? {
        return fragments[position].second
    }
}

