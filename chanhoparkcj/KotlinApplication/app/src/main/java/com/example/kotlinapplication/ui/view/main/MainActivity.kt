package com.example.kotlinapplication.ui.view.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.kotlinapplication.R
import com.example.kotlinapplication.ui.view.home.HomeFragment
import com.orhanobut.hawk.Hawk

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Hawk.init(applicationContext).build()
        supportFragmentManager.beginTransaction()
            .replace(R.id.constraintlayout_main_container, HomeFragment()).commit()
    }
}
