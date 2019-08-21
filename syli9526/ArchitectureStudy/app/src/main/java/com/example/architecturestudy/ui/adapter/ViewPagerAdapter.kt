package com.example.architecturestudy.ui.adapter

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.example.architecturestudy.model.MarketResponse
import com.example.architecturestudy.ui.CoinFragment

class ViewPagerAdapter(
    fm: FragmentManager,
    private val context: Context,
    private val pageList: List<Int>,
    private val marketList: List<MarketResponse>
) : FragmentStatePagerAdapter(fm) {

    private val markets by lazy { setMarkets() }

    override fun getCount(): Int = pageList.size

    override fun getItemPosition(`object`: Any): Int = POSITION_NONE

    override fun getItem(position: Int): Fragment? = CoinFragment.newInstance(markets[position])

    private fun setMarkets(): ArrayList<String> {

        val list = ArrayList<String>()

        for (value in pageList) {
            list.add((marketList.filter { it.market.split("-")[0] == context.getString(value) }
                .map { it.market } as ArrayList<String>).joinToString(","))
        }
        return list
    }

}