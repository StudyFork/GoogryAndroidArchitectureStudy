package com.example.kotlinapplication.ui.view.home

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class PagerAdapter(manager: FragmentManager) : FragmentPagerAdapter(manager) {
    private val fragmentList: ArrayList<Fragment> = ArrayList()
    private val fragmentTitleList: ArrayList<String> = ArrayList()

    override fun getItem(position: Int): Fragment = fragmentList.get(position)


    override fun getCount(): Int = fragmentList.size

    fun addFragment(fragment: Fragment, title: String) {
        fragmentList.add(fragment)
        fragmentTitleList.add(title)
    }

    override fun getPageTitle(position: Int): CharSequence? = fragmentTitleList.get(position)


}