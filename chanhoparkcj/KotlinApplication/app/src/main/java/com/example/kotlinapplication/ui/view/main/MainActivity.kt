package com.example.kotlinapplication.ui.view.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.kotlinapplication.R
import com.example.kotlinapplication.ui.view.home.HomeFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager.beginTransaction().replace(R.id.main_container, HomeFragment()).commit()
    }
}
