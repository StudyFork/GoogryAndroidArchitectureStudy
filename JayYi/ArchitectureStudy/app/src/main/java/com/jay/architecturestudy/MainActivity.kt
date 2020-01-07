package com.jay.architecturestudy

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.jay.architecturestudy.databinding.ActivityMainBinding
import com.jay.architecturestudy.ui.blog.BlogFragment
import com.jay.architecturestudy.ui.image.ImageFragment
import com.jay.architecturestudy.ui.kin.KinFragment
import com.jay.architecturestudy.ui.movie.MovieFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

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
            binding.main.bottomNavigation.menu.getItem(position).isChecked = true
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        setSupportActionBar(binding.toolbar)

        binding.main.bottomNavigation.setOnNavigationItemSelectedListener(
            navigationItemSelectedListener
        )
        binding.main.viewPager.adapter = pagerAdapter
        binding.main.viewPager.addOnPageChangeListener(pageChangeListener)
    }

    private fun setPageWithIndex(index: Int) {
        binding.main.viewPager.currentItem = index
    }

    inner class PagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {
        private val fragments = arrayListOf<Fragment>(
            MovieFragment(), ImageFragment(),
            BlogFragment(), KinFragment()
        )

        override fun getItem(position: Int): Fragment =
            fragments[position]

        override fun getCount(): Int =
            fragments.size

    }
}
