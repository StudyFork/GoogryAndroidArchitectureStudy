package com.architecture.study.adapter

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.architecture.study.R
import com.architecture.study.fragment.CoinListFragment

class TabPagerAdapter : FragmentStatePagerAdapter {
    // Count number of tabs
    private var tabCount: Int
    private var mContext: Context

    constructor(fm: FragmentManager, tabCount: Int, context: Context) : super(fm) {
        this.tabCount = tabCount
        this.mContext = context
    }


    /*4개 탭 one fragment로 bundle argument 처리*/
    override fun getItem(position: Int): Fragment? {
        // Returning the current tabs
        when (position) {
            0 -> {
                return CoinListFragment.newInstance(mContext.getString(R.string.monetary_unit_1))
            }
            1 -> {
                return CoinListFragment.newInstance(mContext.getString(R.string.monetary_unit_2))
            }
            2 -> {
                return CoinListFragment.newInstance(mContext.getString(R.string.monetary_unit_3))
            }
            3 -> {
                return CoinListFragment.newInstance(mContext.getString(R.string.monetary_unit_4))
            }
            else -> return null
        }
    }

    override fun getCount(): Int {
        return tabCount
    }

    override fun getItemPosition(`object`: Any): Int {
        return POSITION_NONE
    }

}