package com.android.studyfork.ui.adpater

import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.android.studyfork.ui.TikcerListFragment

class ViewpagerAdapter(fragmentMnager : FragmentManager) :
    FragmentPagerAdapter(fragmentMnager){

    private var titles: Array<String>? = null
    private var marketDataset : Array<String>? = null

    override fun getItem(position: Int): Fragment  {
        val item = marketDataset?.get(position) ?:""
        return TikcerListFragment.newInstance(item)
    }

    override fun getCount(): Int  = marketDataset?.size ?:0

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        super.destroyItem(container, position, `object`)
    }

    fun setData(items : Array<String>){
        this.marketDataset = items
        notifyDataSetChanged()
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return titles?.get(position)
    }

    fun setTitles(items : Array<String>){
        this.titles = items
    }

}
