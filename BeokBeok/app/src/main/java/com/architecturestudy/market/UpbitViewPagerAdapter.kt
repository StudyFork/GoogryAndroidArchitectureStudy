package com.architecturestudy.market

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.architecturestudy.common.MarketTypes

class UpbitViewPagerAdapter(
    fm: FragmentManager
) : FragmentStatePagerAdapter(fm) {

    override fun getCount(): Int = MarketTypes.values().size

    override fun getItem(position: Int): Fragment {
        val upbitContentsFragment = UpbitContentsFragment()
        upbitContentsFragment.arguments = Bundle().apply {
            putInt("marketType", position)
        }
        return upbitContentsFragment
    }
}