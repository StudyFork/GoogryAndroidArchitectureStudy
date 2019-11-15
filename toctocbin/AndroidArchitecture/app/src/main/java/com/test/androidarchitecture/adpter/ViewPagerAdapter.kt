package com.test.androidarchitecture.adpter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.test.androidarchitecture.ui.ticker.TickerFragment
import com.test.androidarchitecture.data.MarketTitle

class ViewPagerAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {


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
        with(this.marketTitles) {
            clear()
            addAll(marketTitles)
        }
        notifyDataSetChanged()
    }

}