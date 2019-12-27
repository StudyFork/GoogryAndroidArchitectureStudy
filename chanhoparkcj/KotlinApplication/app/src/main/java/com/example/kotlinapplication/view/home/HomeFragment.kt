package com.example.kotlinapplication.view.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.example.kotlinapplication.R
import com.example.kotlinapplication.adapter.PagerAdapter
import kotlinx.android.synthetic.main.fragment_home.*


class HomeFragment : Fragment() {
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
        val pagerAdapter = PagerAdapter(activity!!.supportFragmentManager)
        with(pagerAdapter) {
            addFragment(PageFragment.newInstance(0), "영화")
            addFragment(PageFragment.newInstance(1), "이미지")
            addFragment(PageFragment.newInstance(2), "블로그")
            addFragment(PageFragment.newInstance(3), "지식인")
        }
        viewpager = home_viewpager
        with(viewpager) {
            adapter = pagerAdapter
        }
        home_tab.setupWithViewPager(viewpager)
    }


}
