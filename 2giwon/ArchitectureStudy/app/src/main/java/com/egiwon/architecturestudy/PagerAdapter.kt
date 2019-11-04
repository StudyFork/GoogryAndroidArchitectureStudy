package com.egiwon.architecturestudy

import androidx.annotation.IntRange
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.egiwon.architecturestudy.tabs.ContentsFragment

class PagerAdapter(fm: FragmentManager?) : FragmentStatePagerAdapter(fm) {

    override fun getItem(@IntRange(from = 0, to = 3) position: Int): Fragment =
        when (position) {
            0 -> ContentsFragment.newInstance("blog")
            1 -> ContentsFragment.newInstance("news")
            2 -> ContentsFragment.newInstance("movie")
            3 -> ContentsFragment.newInstance("book")
            else -> throw IllegalArgumentException()
        }

    override fun getCount(): Int = MAX_PAGE

    companion object {
        private const val MAX_PAGE = 4
    }
}