package com.example.seonoh.seonohapp

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

class TabPagerAdapter(
    fm: FragmentManager
) : FragmentStatePagerAdapter(fm) {

    private lateinit var mData: ArrayList<String>

    fun setData( data: ArrayList<String>){
        mData = data
    }

    override fun getCount(): Int = if(::mData.isInitialized) mData.size else 0

    override fun getItem(position: Int): Fragment = CoinFragment.newInstance(mData[position])

}


