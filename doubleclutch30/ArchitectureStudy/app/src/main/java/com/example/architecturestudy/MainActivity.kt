package com.example.architecturestudy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.architecturestudy.ui.PagerAdapter
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.example.architecturestudy.ui.blog.BlogFragment
import com.example.architecturestudy.ui.image.ImageFragment
import com.example.architecturestudy.ui.kin.KinFragment
import com.example.architecturestudy.ui.movie.MovieFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val adapter = PagerAdapter(supportFragmentManager)
        viewPager.adapter = adapter


        bottom_navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)


    }

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when(item.itemId) {
            R.id.bottom_nav_movie -> {
                println("Movie")
                replaceFragment(MovieFragment())
                return@OnNavigationItemSelectedListener true
            }
            R.id.bottom_nav_blog -> {
                println("Blog")
                replaceFragment(BlogFragment())
                return@OnNavigationItemSelectedListener true
            }
            R.id.bottom_nav_kin -> {
                println("Kin")
                replaceFragment(KinFragment())
                return@OnNavigationItemSelectedListener true
            }
            R.id.bottom_nav_image -> {
                print("Image")
                replaceFragment(ImageFragment())
                return@OnNavigationItemSelectedListener true
            }

        }
        false
    }

     private fun replaceFragment(fragment: Fragment) {
         val fragmentTransaction = supportFragmentManager.beginTransaction()
         fragmentTransaction.replace(R.id.fragmentContainer, fragment)
         fragmentTransaction.commit()
     }

}
