package com.architecturestudy.common

import com.google.android.material.tabs.TabLayout

abstract class TabSelectedListener : TabLayout.OnTabSelectedListener {
    override fun onTabReselected(tab: TabLayout.Tab?) {
        // NO OP
    }

    override fun onTabUnselected(tab: TabLayout.Tab?) {
        // NO OP
    }

    override fun onTabSelected(tab: TabLayout.Tab?) {
        // NO OP
    }
}