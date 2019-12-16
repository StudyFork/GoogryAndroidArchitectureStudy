package com.example.studyapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.studyapplication.main.blog.BlogFragment
import com.example.studyapplication.main.image.ImageFragment
import com.example.studyapplication.main.kin.KinFragment
import com.example.studyapplication.main.movie.MovieFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        showMovieFragment()
        bottomView.setOnNavigationItemSelectedListener(tabSelectedListener())
    }

    // 영화 검색 화면 노출
    private fun showMovieFragment() {
        supportFragmentManager.beginTransaction()
            .add(R.id.frameLayout, MovieFragment.newInstance())
            .commitAllowingStateLoss()
    }

    // 화면 교체
    private fun replaceFragment(fragment : Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.frameLayout, fragment)
            .commitAllowingStateLoss()
    }

    // 탭 클릭 리스너
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