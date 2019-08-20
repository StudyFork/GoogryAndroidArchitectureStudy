package com.example.seonoh.seonohapp

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

class TabPagerAdapter(
    fm: FragmentManager,
    private val data: ArrayList<String>,
    private val tabcount: Int
) : FragmentStatePagerAdapter(fm) {

    override fun getCount(): Int = tabcount

    override fun getItem(position: Int): Fragment = CoinFragment.newInstance(data[position])

}


