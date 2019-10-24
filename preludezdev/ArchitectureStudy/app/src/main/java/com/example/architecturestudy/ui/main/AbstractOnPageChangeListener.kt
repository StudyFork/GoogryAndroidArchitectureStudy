package com.example.architecturestudy.ui.main

import androidx.viewpager.widget.ViewPager

abstract class AbstractOnPageChangeListener : ViewPager.OnPageChangeListener {
    override fun onPageScrollStateChanged(state: Int) {}
    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}
    override fun onPageSelected(position: Int) {}
}