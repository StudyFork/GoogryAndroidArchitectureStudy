package com.example.architecturestudy.ui

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class PagerAdapter(fm : FragmentManager) : FragmentPagerAdapter(fm) {

    private val fragmentList : MutableList<Fragment> = ArrayList()


    override fun getItem(position: Int): Fragment {
        return fragmentList[position]
    }

    override fun getCount(): Int {
        return fragmentList.size
    }

}
