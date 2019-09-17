package com.android.studyfork.ui.main.adapter

import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.android.studyfork.ui.TickerListFragment

/**
 * created by onemask
 */
class ViewPagerAdapter(fragmentManager: FragmentManager) :
    FragmentPagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    private var titles: List<String> = listOf("")
    private var marketDataSet: Array<String> = arrayOf()

    override fun getItem(position: Int): Fragment {
        val item = marketDataSet[position]
        return TickerListFragment.newInstance(item)
    }

    override fun getCount(): Int = marketDataSet.size

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        super.destroyItem(container, position, `object`)
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return titles[position]
    }

    fun setTitles(titles : List<String>){
        this.titles = titles
        notifyDataSetChanged()
    }

    fun setData(items: Array<String>) {
        this.marketDataSet = items
        notifyDataSetChanged()
    }

}