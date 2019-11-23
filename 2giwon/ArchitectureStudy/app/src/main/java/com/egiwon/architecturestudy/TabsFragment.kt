package com.egiwon.architecturestudy

import android.os.Bundle
import com.egiwon.architecturestudy.base.BaseFragment
import com.egiwon.architecturestudy.base.BasePresenter
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.fg_tabs.*

class TabsFragment : BaseFragment(
    R.layout.fg_tabs
) {
    override val presenter: BasePresenter = TabsPresenter()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initViewPager()
    }

    private fun initViewPager() {
        vp_contents.apply {
            adapter = fragmentManager?.let { PagerAdapter(it) }
            addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tl_search))
        }

        with(tl_search) {
            addTab(newTab().setText(R.string.blog_tab))
            addTab(newTab().setText(R.string.news_tab))
            addTab(newTab().setText(R.string.movie_tab))
            addTab(newTab().setText(R.string.book_tab))
            addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
                override fun onTabSelected(tab: TabLayout.Tab?) {
                    tab?.run { vp_contents.currentItem = position }
                }

                override fun onTabReselected(tab: TabLayout.Tab?) = Unit
                override fun onTabUnselected(tab: TabLayout.Tab?) = Unit
            })
        }
    }
}