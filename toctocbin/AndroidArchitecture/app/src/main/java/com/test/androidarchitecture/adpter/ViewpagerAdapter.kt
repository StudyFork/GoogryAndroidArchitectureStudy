package com.test.androidarchitecture.adpter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.test.androidarchitecture.CoinFragment
import com.test.androidarchitecture.data.MarketTitle

class ViewpagerAdapter(fm: FragmentManager?) : FragmentStatePagerAdapter(fm) {


    private var marketTitles: List<MarketTitle> = mutableListOf()

    override fun getItem(position: Int): Fragment {
        return CoinFragment.getInstance(marketTitles[position].marketSearch)
    }

    override fun getCount(): Int {
        return marketTitles.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return marketTitles[position].marketTitle
    }

    fun setData(marketTitles: List<MarketTitle>){
        this.marketTitles = marketTitles
        notifyDataSetChanged()
    }

}