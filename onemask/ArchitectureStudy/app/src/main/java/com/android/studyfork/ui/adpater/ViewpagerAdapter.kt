package com.android.studyfork.ui.adpater

import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.android.studyfork.ui.TickerListFragment

class ViewpagerAdapter(fragmentManager : FragmentManager) :
    FragmentPagerAdapter(fragmentManager){

    private var titles: Array<String>? = null
    private var marketDataSet : Array<String>? = null

    override fun getItem(position: Int): Fragment  {
        val item = marketDataSet?.get(position) ?:""
        return TickerListFragment.newInstance(item)
    }

    override fun getCount(): Int  = marketDataSet?.size ?:0

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        super.destroyItem(container, position, `object`)
    }

    fun setData(items : Array<String>){
        this.marketDataSet = items
        notifyDataSetChanged()
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return titles?.get(position)
    }

    fun setTitles(items : Array<String>){
        this.titles = items
    }

}
