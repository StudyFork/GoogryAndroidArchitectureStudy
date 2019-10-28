package com.egiwon.architecturestudy

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.egiwon.architecturestudy.tabs.BlogFragment

class PagerAdapter(fm: FragmentManager?) : FragmentStatePagerAdapter(fm) {

    override fun getItem(position: Int): Fragment =
        when (position) {
            0, 1, 2, 3 -> BlogFragment()
            else -> throw IllegalArgumentException()
        }

    override fun getCount(): Int = MAX_PAGE

    companion object {
        private const val MAX_PAGE = 4
    }
}