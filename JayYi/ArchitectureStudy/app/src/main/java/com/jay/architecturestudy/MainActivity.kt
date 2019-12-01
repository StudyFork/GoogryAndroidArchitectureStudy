package com.jay.architecturestudy

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.jay.architecturestudy.ui.MovieFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {

    private val pagerAdapter = PagerAdapter(supportFragmentManager)

    private val navigationItemSelectedListener =
        BottomNavigationView.OnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.menu_movie -> {
                    setPageWithIndex(0)
                    true
                }
                R.id.menu_image -> {
                    setPageWithIndex(1)
                    true
                }
                R.id.menu_blog -> {
                    setPageWithIndex(2)
                    true
                }
                R.id.menu_kin -> {
                    setPageWithIndex(3)
                    true
                }
                else -> {
                    false
                }
            }
        }

    private val pageChangeListener = object : ViewPager.OnPageChangeListener {
        override fun onPageScrollStateChanged(state: Int) {
        }

        override fun onPageScrolled(
            position: Int,
            positionOffset: Float,
            positionOffsetPixels: Int
        ) {
        }

        override fun onPageSelected(position: Int) {
            bottom_navigation.menu.getItem(position).setChecked(true)
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        bottom_navigation.setOnNavigationItemSelectedListener(navigationItemSelectedListener)
        view_pager.adapter = pagerAdapter
        view_pager.addOnPageChangeListener(pageChangeListener)
    }

    private fun setPageWithIndex(index: Int) {
        view_pager?.currentItem = index
    }

    inner class PagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {
        private val fragments = arrayListOf(
            MovieFragment()
        )

        override fun getItem(position: Int): Fragment {
            return fragments[position]
        }

        override fun getCount(): Int {
            return fragments.size
        }

    }
}
