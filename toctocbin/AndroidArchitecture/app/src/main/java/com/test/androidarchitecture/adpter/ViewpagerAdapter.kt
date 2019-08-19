package com.test.androidarchitecture.adpter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.test.androidarchitecture.CoinFragment
import com.test.androidarchitecture.data.MarketTitle

class ViewpagerAdapter(
    fm: FragmentManager?,
    private val marketTitles: List<MarketTitle>
) : FragmentStatePagerAdapter(fm) {


    override fun getItem(position: Int): Fragment {
        return CoinFragment.getInstance(marketTitles[position].marketSearch)
    }

    override fun getCount(): Int {
        return marketTitles.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return marketTitles[position].marketTitle
    }
}