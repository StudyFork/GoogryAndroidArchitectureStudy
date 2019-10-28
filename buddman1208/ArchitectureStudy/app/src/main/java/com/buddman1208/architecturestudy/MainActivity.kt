package com.buddman1208.architecturestudy

import android.os.Bundle
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initToolbar()
        initBottomNavigation()
    }

    private fun initToolbar() {
        toolbar.apply {
            setSupportActionBar(this)
            setTitleTextColor(ContextCompat.getColor(applicationContext, android.R.color.white))
            setBackgroundColor(ContextCompat.getColor(applicationContext, R.color.colorPrimary))
            contentInsetStartWithNavigation = 0
        }
        updateToolbarTitle(bottomTabView.selectedItemId)
    }

    private fun initBottomNavigation() {
        bottomTabView.setOnNavigationItemSelectedListener {
            updateToolbarTitle(it.itemId)
            true
        }
    }

    private fun updateToolbarTitle(@IdRes itemId: Int) {
        supportActionBar?.title = when (itemId) {
            R.id.menuBlog -> "블로그"
            R.id.menuBook -> "도서"
            R.id.menuMovie -> "영화"
            R.id.menuNews -> "뉴스"
            else -> ""
        }
    }

}
