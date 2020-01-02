package com.ironelder.androidarchitecture.ui

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.ironelder.androidarchitecture.R
import com.ironelder.androidarchitecture.common.*
import com.ironelder.androidarchitecture.view.mainview.MainFragment

class CustomPagerAdapter(private val context: Context, fm: FragmentManager) :
    FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    override fun getCount(): Int {
        return TAB_MAX_CNT
    }

    override fun getItem(position: Int): Fragment {
        return when (position) {
            1 -> MainFragment.newInstance(
                NEWS
            )
            2 -> MainFragment.newInstance(
                BOOK
            )
            3 -> MainFragment.newInstance(
                MOVIE
            )
            else -> MainFragment.newInstance(
                BLOG
            )
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when (position) {
            1 -> context.getString(R.string.tab_news)
            2 -> context.getString(R.string.tab_book)
            3 -> context.getString(R.string.tab_movie)
            else -> context.getString(R.string.tab_blog)
        }
    }
}