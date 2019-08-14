package com.jskim5923.architecturestudy.adapter

import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.jskim5923.architecturestudy.ui.CoinFragment

class ViewPagerAdapter(
    fm: FragmentManager,
    private val titleList: List<String>
) : FragmentStatePagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getItem(position: Int) = CoinFragment()

    override fun getCount() = titleList.size

    override fun getPageTitle(position: Int) = titleList[position]
}