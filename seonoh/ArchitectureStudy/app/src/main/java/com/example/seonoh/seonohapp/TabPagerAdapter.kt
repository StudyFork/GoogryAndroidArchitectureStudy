package com.example.seonoh.seonohapp

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

class TabPagerAdapter(
    fm: FragmentManager
) : FragmentStatePagerAdapter(fm) {

    private val mData = mutableListOf<String>()

    fun setData( data: ArrayList<String>){
        mData.addAll(data)
    }

    override fun getCount(): Int =  mData.size

    override fun getItem(position: Int): Fragment = CoinFragment.newInstance(mData[position])

}


