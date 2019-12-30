package com.example.kotlinapplication.ui.view.home

import android.os.Bundle
import android.view.View
import com.example.kotlinapplication.R
import com.example.kotlinapplication.ui.base.BaseFragment
import com.example.kotlinapplication.ui.view.page.blog.BlogFragment
import com.example.kotlinapplication.ui.view.page.image.ImageFragment
import com.example.kotlinapplication.ui.view.page.kin.KinFragment
import com.example.kotlinapplication.ui.view.page.movie.MovieFragment
import kotlinx.android.synthetic.main.fragment_home.*


class HomeFragment : BaseFragment(R.layout.fragment_home) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        home_tab.setupWithViewPager(home_viewpager)
        home_viewpager.adapter = PagerAdapter(activity!!.supportFragmentManager)
            .apply {
            addFragment(MovieFragment(), "영화")
            addFragment(ImageFragment(), "이미지")
            addFragment(BlogFragment(), "블로그")
            addFragment(KinFragment(), "지식인")
        }
    }
}
