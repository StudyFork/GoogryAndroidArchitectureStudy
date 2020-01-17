package com.egiwon.architecturestudy.ui

import android.os.Bundle
import com.egiwon.architecturestudy.R
import com.egiwon.architecturestudy.base.BaseFragment
import com.egiwon.architecturestudy.base.BaseViewModel
import com.egiwon.architecturestudy.databinding.FgTabsBinding
import com.google.android.material.tabs.TabLayout

class TabsFragment(
    override val viewModel: BaseViewModel
) : BaseFragment<FgTabsBinding, BaseViewModel>(R.layout.fg_tabs) {

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initViewPager()
    }

    private fun initViewPager() {
        bind {
            vpContents.run {
                adapter = fragmentManager?.let { PagerAdapter(it) }
                addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tlSearch))
            }

            with(tlSearch) {
                addTab(newTab().setText(R.string.blog_tab))
                addTab(newTab().setText(R.string.news_tab))
                addTab(newTab().setText(R.string.movie_tab))
                addTab(newTab().setText(R.string.book_tab))
                addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
                    override fun onTabSelected(tab: TabLayout.Tab?) {
                        tab?.run { vpContents.currentItem = position }
                    }

                    override fun onTabReselected(tab: TabLayout.Tab?) = Unit
                    override fun onTabUnselected(tab: TabLayout.Tab?) = Unit
                })
            }
        }

    }
}