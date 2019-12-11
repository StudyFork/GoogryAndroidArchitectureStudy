package com.example.architecturestudy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottom_navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

    }

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when(item.itemId) {
            R.id.bottom_nav_movie -> {
                println("Movie")
                return@OnNavigationItemSelectedListener true
            }
            R.id.bottom_nav_blog -> {
                println("Blog")
                return@OnNavigationItemSelectedListener true
            }
            R.id.bottom_nav_kin -> {
                println("Kin")
                return@OnNavigationItemSelectedListener true
            }
            R.id.bottom_nav_image -> {
                print("iamge")
                return@OnNavigationItemSelectedListener true
            }

        }
        false
    }
}
