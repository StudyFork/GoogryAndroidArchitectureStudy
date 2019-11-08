package com.god.taeiim.myapplication.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.god.taeiim.myapplication.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottomNavigation.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)
        loadFragment(bottomNavigation.selectedItemId)
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
            R.id.blogNavigation -> ContentsFragment("blog")
            R.id.newsNavigation -> ContentsFragment("news")
            R.id.movieNavigation -> ContentsFragment("movie")
            R.id.bookNavigation -> ContentsFragment("book")
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
