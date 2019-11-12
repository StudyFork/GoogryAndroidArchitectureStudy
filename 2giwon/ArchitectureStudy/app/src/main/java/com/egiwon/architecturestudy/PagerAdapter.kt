package com.egiwon.architecturestudy

import androidx.annotation.IntRange
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.egiwon.architecturestudy.tabs.ContentsFragment
import com.egiwon.architecturestudy.tabs.Tab

class PagerAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(
    fm,
    BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
) {

    override fun getItem(
        @IntRange(from = 0, to = 3)
        position: Int
    ): Fragment =
        when (position) {
            0 -> ContentsFragment.newInstance(Tab.BLOG)
            1 -> ContentsFragment.newInstance(Tab.NEWS)
            2 -> ContentsFragment.newInstance(Tab.MOVIE)
            3 -> ContentsFragment.newInstance(Tab.BOOK)
            else -> throw IllegalArgumentException()
        }

    override fun getCount(): Int = MAX_PAGE

    companion object {
        private const val MAX_PAGE = 4
    }
}