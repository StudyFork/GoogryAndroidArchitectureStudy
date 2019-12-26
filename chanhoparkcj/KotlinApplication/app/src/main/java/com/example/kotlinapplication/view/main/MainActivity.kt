package com.example.kotlinapplication.view.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.kotlinapplication.view.home.HomeFragment
import com.example.kotlinapplication.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        start()
    }

    private fun start() {
        val manager = supportFragmentManager
        manager.beginTransaction().replace(
            R.id.main_container,
            HomeFragment()
        ).commit()
    }
}
