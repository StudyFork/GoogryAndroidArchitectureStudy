package com.example.seonoh.seonohapp

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

class TabPagerAdapter(
    fm: FragmentManager
) : FragmentStatePagerAdapter(fm) {

    private val data = mutableListOf<String>()

    fun setData(data: List<String>) {
        this.data.addAll(data)
        notifyDataSetChanged()
    }

    override fun getPageTitle(position: Int): CharSequence? = data[position].substringBefore("-")

    override fun getCount(): Int = data.size

    override fun getItem(position: Int): Fragment = CoinFragment.newInstance(data[position])

}


