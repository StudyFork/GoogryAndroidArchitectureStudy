package com.ironelder.androidarchitecture.component

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.ironelder.androidarchitecture.R


class MainActivity : BaseActivity() {
    override fun doCreate(savedInstanceState: Bundle?) {
        super.doCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val pageAdapter = PagerAdapter(supportFragmentManager)
        val pager = findViewById<ViewPager>(R.id.viewPager)
        pager.adapter = pageAdapter

        val tab = findViewById<TabLayout>(R.id.tabLayout)
        tab.setupWithViewPager(pager)

    }
}

class PagerAdapter(fm: FragmentManager): FragmentPagerAdapter(fm)
{
    val PAGE_MAX_CNT = 4

    override fun getCount(): Int {
        return PAGE_MAX_CNT
    }

    override fun getItem(position: Int): Fragment? {
        val fragment = when(position)
        {
            1 -> MainFragment("news")
            2 -> MainFragment("book")
            3 -> MainFragment("movie")
            else -> MainFragment("blog")
        }
        return fragment
    }

    override fun getPageTitle(position: Int): CharSequence? {
        val title = when(position)
        {
            1 -> "NEWS"
            2 -> "BOOK"
            3 -> "MOVIE"
            else -> "BLOG"
        }
        return title
    }
}