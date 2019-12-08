package com.example.kotlinapplication.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.example.kotlinapplication.R
import com.example.kotlinapplication.adapter.PagerAdapter
import com.example.kotlinapplication.fragment.FragmentPage
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.fragment_home.*


class HomeFragment : Fragment() {
    private lateinit var tabs: TabLayout
    private lateinit var viewpager: ViewPager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        start()
    }

    private fun start() {
        setUpViewPager()
    }

    private fun setUpViewPager() {
        val adapter = PagerAdapter(activity!!.supportFragmentManager)
        adapter.addFragment(FragmentPage.newInstance("영화"), "영화")
        adapter.addFragment(FragmentPage.newInstance("이미지"), "이미지")
        adapter.addFragment(FragmentPage.newInstance("블로그"), "블로그")
        adapter.addFragment(FragmentPage.newInstance("지식인"), "지식인")

        viewpager = home_viewpager
        viewpager.offscreenPageLimit = 4
        viewpager.adapter = adapter

        tabs = home_tab
        tabs.setupWithViewPager(viewpager)

    }


}
