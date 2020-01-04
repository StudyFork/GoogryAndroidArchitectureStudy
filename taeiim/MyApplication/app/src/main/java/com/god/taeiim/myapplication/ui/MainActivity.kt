package com.god.taeiim.myapplication.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.god.taeiim.myapplication.R
import com.god.taeiim.myapplication.Tabs
import com.god.taeiim.myapplication.base.BaseActivity
import com.god.taeiim.myapplication.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        with(binding) {
            bottomNavigation.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)
            loadFragment(bottomNavigation.selectedItemId)
        }
    }

    private val onNavigationItemSelectedListener =
        BottomNavigationView.OnNavigationItemSelectedListener { item ->
            loadFragment(item.itemId)
            true
        }

    private fun loadFragment(itemId: Int) {
        supportFragmentManager.findFragmentByTag(
            itemId.toString()
        ) ?: when (itemId) {
            R.id.blogNavigation -> ContentsFragment.newInstance(Tabs.BLOG)
            R.id.newsNavigation -> ContentsFragment.newInstance(Tabs.NEWS)
            R.id.movieNavigation -> ContentsFragment.newInstance(Tabs.MOVIE)
            R.id.bookNavigation -> ContentsFragment.newInstance(Tabs.BOOK)
            else -> null
        }?.let { replaceFragment(it) }
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragmentContainer, fragment, fragment.tag)
            .commit()
    }
}
