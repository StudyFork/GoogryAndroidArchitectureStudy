package com.example.naversearchapistudy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment

import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.google.android.material.bottomnavigation.BottomNavigationItemView
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val pagerApapter = PagerAdapter(this)
        pager.adapter = pagerApapter

        bottom_navigation.setOnNavigationItemReselectedListener {
            when(it.itemId) {
                R.id.navigation_movie -> setPageIndex(0)
                R.id.navigation_blog -> setPageIndex(1)
                R.id.navigation_kin -> setPageIndex(2)
                else -> false
            }
        }
    }



    private val pageChangeListener = object : ViewPager.OnPageChangeListener {
        override fun onPageScrollStateChanged(state: Int) {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun onPageScrolled(
            position: Int,
            positionOffset: Float,
            positionOffsetPixels: Int
        ) {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun onPageSelected(position: Int) {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

    }

    private fun setPageIndex(index: Int) {
        pager?.currentItem = index
    }

}
