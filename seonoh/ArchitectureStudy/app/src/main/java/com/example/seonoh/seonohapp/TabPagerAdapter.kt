package com.example.seonoh.seonohapp

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

class TabPagerAdapter(
    fm: FragmentManager,
    private val data: ArrayList<String>
) : FragmentStatePagerAdapter(fm) {

    override fun getCount(): Int = data.size

    override fun getItem(position: Int): Fragment = CoinFragment.newInstance(data[position])

}


