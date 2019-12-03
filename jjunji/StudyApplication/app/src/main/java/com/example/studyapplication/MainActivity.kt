package com.example.studyapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.studyapplication.blog.BlogFragment
import com.example.studyapplication.image.ImageFragment
import com.example.studyapplication.kin.KinFragment
import com.example.studyapplication.movie.MovieFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val fragmentManager : FragmentManager = supportFragmentManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        showMovieFragment()
        bottomView.setOnNavigationItemSelectedListener(tabSelectedListener())
    }

    private fun showMovieFragment() {
        fragmentManager.beginTransaction()
            .add(R.id.frameLayout, MovieFragment.newInstance())
            .commitAllowingStateLoss()
    }

    private fun replaceFragment(fragment : Fragment) {
        fragmentManager.beginTransaction()
            .replace(R.id.frameLayout, fragment)
            .commitAllowingStateLoss()
    }

    private fun tabSelectedListener(): BottomNavigationView.OnNavigationItemSelectedListener {
        return BottomNavigationView.OnNavigationItemSelectedListener { tab ->
            when (tab.itemId) {
                R.id.tabMovie -> replaceFragment(MovieFragment.newInstance())
                R.id.tabImage -> replaceFragment(ImageFragment.newInstance())
                R.id.tabBlog -> replaceFragment(BlogFragment.newInstance())
                R.id.tabKin -> replaceFragment(KinFragment.newInstance())
            }
            true
        }
    }
}