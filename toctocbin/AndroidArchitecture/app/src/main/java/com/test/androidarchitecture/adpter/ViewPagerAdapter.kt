package com.test.androidarchitecture.adpter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.test.androidarchitecture.TickerFragment
import com.test.androidarchitecture.data.MarketTitle

class ViewPagerAdapter(fm: FragmentManager?) : FragmentStatePagerAdapter(fm) {


    private val marketTitles = mutableListOf<MarketTitle>()

    override fun getItem(position: Int): Fragment {
        return TickerFragment.getInstance(marketTitles[position].marketSearch)
    }

    override fun getCount(): Int {
        return marketTitles.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return marketTitles[position].marketTitle
    }

    fun setData(marketTitles: List<MarketTitle>) {
        this.marketTitles.clear()
        this.marketTitles.addAll(marketTitles)
        notifyDataSetChanged()
    }

}