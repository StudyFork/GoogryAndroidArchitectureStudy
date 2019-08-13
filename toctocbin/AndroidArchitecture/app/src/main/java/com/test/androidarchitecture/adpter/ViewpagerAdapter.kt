package com.test.androidarchitecture.adpter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

class ViewpagerAdapter(fm: FragmentManager?,
                       private val fragmentList: ArrayList<Fragment>,
                       private val titleList: ArrayList<String>) : FragmentStatePagerAdapter(fm) {


    override fun getItem(position: Int): Fragment {
        return fragmentList[position]
    }

    override fun getCount(): Int {
        return fragmentList.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return titleList[position]
    }
}