package com.jskim5923.architecturestudy.adapter

import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.jskim5923.architecturestudy.extension.getCoinCurrency
import com.jskim5923.architecturestudy.ui.CoinFragment

class ViewPagerAdapter(fm: FragmentManager) :
    FragmentStatePagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    private var titleList = mutableListOf<String>()

    override fun getItem(position: Int) =
        CoinFragment.newInstance(titleList[position].getCoinCurrency())

    override fun getCount() = titleList.size

    override fun getPageTitle(position: Int) = titleList[position]

    fun updateItem(itemList: List<String>) {
        titleList.clear()
        titleList.addAll(itemList)
        notifyDataSetChanged()
    }
}