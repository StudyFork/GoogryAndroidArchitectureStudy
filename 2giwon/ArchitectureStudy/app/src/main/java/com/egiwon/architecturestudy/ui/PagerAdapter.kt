package com.egiwon.architecturestudy.ui

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.egiwon.architecturestudy.ui.tabs.ContentFragment

class PagerAdapter(fm: FragmentManager) :
    FragmentStatePagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getItem(position: Int): Fragment =
        ContentFragment.newInstance(Tab.values()[position])

    override fun getCount(): Int = Tab.values().size

}