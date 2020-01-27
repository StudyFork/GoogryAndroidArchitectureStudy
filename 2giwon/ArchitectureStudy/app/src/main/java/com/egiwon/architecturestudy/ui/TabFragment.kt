package com.egiwon.architecturestudy.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.egiwon.architecturestudy.R
import com.egiwon.architecturestudy.databinding.FgTabBinding
import com.google.android.material.tabs.TabLayout

class TabFragment : Fragment() {

    private lateinit var binding: FgTabBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fg_tab, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewPager()
    }

    private fun initViewPager() {
        binding.run {
            vpContent.run {
                adapter = fragmentManager?.let { PagerAdapter(it) }
                addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tlSearch))
            }

            with(tlSearch) {
                Tab.values().forEach { addTab(newTab().setText(it.stringResId)) }
                addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
                    override fun onTabSelected(tab: TabLayout.Tab?) {
                        tab?.run { vpContent.currentItem = position }
                    }

                    override fun onTabReselected(tab: TabLayout.Tab?) = Unit
                    override fun onTabUnselected(tab: TabLayout.Tab?) = Unit
                })
            }
        }
    }
}