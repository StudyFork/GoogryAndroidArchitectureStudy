package com.example.naversearchapistudy

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class PagerAdapter(fragment: FragmentActivity) : FragmentStateAdapter(fragment) {

    private val fragmentList = listOf(MovieFragment(), BlogFragment(), KinFragment())

    override fun getItemCount(): Int = fragmentList.size


    override fun createFragment(position: Int): Fragment = fragmentList[position]

}