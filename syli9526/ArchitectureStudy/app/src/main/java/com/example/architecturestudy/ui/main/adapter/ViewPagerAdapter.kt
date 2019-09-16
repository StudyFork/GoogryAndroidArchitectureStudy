package com.example.architecturestudy.ui.main.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.example.architecturestudy.network.model.MarketResponse
import com.example.architecturestudy.ui.coin.CoinFragment

class ViewPagerAdapter(
    fm: FragmentManager
) : FragmentStatePagerAdapter(fm) {

    private  var markets =  ArrayList<String>()

    override fun getCount(): Int = markets.size

    override fun getItemPosition(`object`: Any): Int = POSITION_NONE

    override fun getItem(position: Int): Fragment? = CoinFragment.newInstance(markets[position])

    fun setData(pageList: List<String>, marketList: List<MarketResponse>) {

        val list = ArrayList<String>()

        for (value in pageList) {
            list.add((marketList.filter { it.market.split("-")[0] == value }
                .map { it.market } as ArrayList<String>).joinToString(","))
        }
        markets = list
        notifyDataSetChanged()
    }

}