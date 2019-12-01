package com.egiwon.architecturestudy

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.egiwon.architecturestudy.tabs.ContentsFragment

class PagerAdapter(fm: FragmentManager) :
    FragmentStatePagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getItem(position: Int): Fragment =
        ContentsFragment.newInstance(Tab.values()[position])

    override fun getCount(): Int = Tab.values().size

}