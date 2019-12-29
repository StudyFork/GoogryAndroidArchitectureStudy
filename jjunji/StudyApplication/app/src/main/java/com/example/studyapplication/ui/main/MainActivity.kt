package com.example.studyapplication.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.studyapplication.R
import com.example.studyapplication.ui.base.SearchFragment
import com.example.studyapplication.ui.main.movie.MovieFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(R.layout.activity_main), MainContract.View {
    private val presenter = MainPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter.init()
        bottomView.setOnNavigationItemSelectedListener(tabSelectedListener())
    }

    // 탭 클릭 리스너
    private fun tabSelectedListener(): BottomNavigationView.OnNavigationItemSelectedListener {
        return BottomNavigationView.OnNavigationItemSelectedListener { tab ->
            presenter.clickTab(tab.itemId)
            true
        }
    }

    override fun showMovieFragment(fragment: MovieFragment) {
        supportFragmentManager.beginTransaction()
            .add(R.id.frameLayout, fragment)
            .commitAllowingStateLoss()
    }

    override fun showSearchFragment(fragment: SearchFragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.frameLayout, fragment)
            .commitAllowingStateLoss()
    }

}