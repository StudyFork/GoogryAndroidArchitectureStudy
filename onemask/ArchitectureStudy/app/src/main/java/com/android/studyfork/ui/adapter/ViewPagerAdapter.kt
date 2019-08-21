package com.android.studyfork.ui.adapter

import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.android.studyfork.ui.TickerListFragment

/**
 * created by onemask
 */
class ViewpagerAdapter(fragmentManager : FragmentManager) :
    FragmentPagerAdapter(fragmentManager){

    private var titles: List<String> = listOf("")
    private var marketDataSet: List<String> = listOf()

    override fun getItem(position: Int): Fragment {
        val item = marketDataSet[position]
        return TickerListFragment.newInstance(item)
    }

    override fun getCount(): Int = marketDataSet.size

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        super.destroyItem(container, position, `object`)
    }

    fun setData(items : Array<String>){
        this.marketDataSet = items.toList()
        notifyDataSetChanged()
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return titles[position]
    }

    fun setTitles(items : Array<String>){
        this.titles = items.toList()
    }

}