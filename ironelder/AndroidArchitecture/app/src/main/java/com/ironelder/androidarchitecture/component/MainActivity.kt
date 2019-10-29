package com.ironelder.androidarchitecture.component

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.ironelder.androidarchitecture.R
import com.ironelder.androidarchitecture.common.*
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : BaseActivity() {
    override fun doCreate(savedInstanceState: Bundle?) {
        super.doCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewPager.adapter = PagerAdapter(supportFragmentManager)
        tabLayout.setupWithViewPager(viewPager)
    }

    inner class PagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {
        override fun getCount(): Int {
            return TAB_MAX_CNT
        }

        override fun getItem(position: Int): Fragment? {
            return when (position) {
                1 -> MainFragment(NEWS)
                2 -> MainFragment(BOOK)
                3 -> MainFragment(MOVIE)
                else -> MainFragment(BLOG)
            }
        }

        override fun getPageTitle(position: Int): CharSequence? {
            return when (position) {
                1 -> getString(R.string.tab_news)
                2 -> getString(R.string.tab_book)
                3 -> getString(R.string.tab_movie)
                else -> getString(R.string.tab_blog)
            }
        }
    }
}

