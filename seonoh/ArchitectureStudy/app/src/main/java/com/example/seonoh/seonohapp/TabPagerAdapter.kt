package com.example.seonoh.seonohapp

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import java.lang.NullPointerException

class TabPagerAdapter : FragmentStatePagerAdapter {

    private var tabCount = 0

    constructor(fm: FragmentManager, tabcount: Int) : super(fm) {
        this.tabCount = tabcount
    }


    override fun getItem(position: Int): Fragment {
        when (position) {
            0 -> return KrwFrgment.newInstance()

            1 -> return BtcFragmnet.newInstance()

            2 -> return EthFragment.newInstance()

            3 -> return UsdtFragment.newInstance()

            else -> return throw NullPointerException()
        }
    }

    override fun getCount(): Int = tabCount
}