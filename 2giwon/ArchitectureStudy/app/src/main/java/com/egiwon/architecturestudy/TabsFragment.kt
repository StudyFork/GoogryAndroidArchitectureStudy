package com.egiwon.architecturestudy

import android.os.Bundle
import com.egiwon.architecturestudy.base.BaseFragment
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.fg_tabs.*

class TabsFragment : BaseFragment(
    R.layout.fg_tabs
) {
    private val tlSearch by lazy { tl_search }
    private val vpContents by lazy { vp_contents }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initViewPager()
    }

    private fun initViewPager() {
        vpContents?.run {
            adapter = fragmentManager?.let { PagerAdapter(it) }
            addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tlSearch))
        }


        tlSearch?.run {
            addTab(newTab().setText(R.string.blog_tab))
            addTab(newTab().setText(R.string.news_tab))
            addTab(newTab().setText(R.string.movie_tab))
            addTab(newTab().setText(R.string.book_tab))
            addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
                override fun onTabSelected(tab: TabLayout.Tab?) {
                    tab?.let { vpContents.currentItem = it.position }
                }

                override fun onTabReselected(tab: TabLayout.Tab?) = Unit
                override fun onTabUnselected(tab: TabLayout.Tab?) = Unit
            })
        }
    }
}