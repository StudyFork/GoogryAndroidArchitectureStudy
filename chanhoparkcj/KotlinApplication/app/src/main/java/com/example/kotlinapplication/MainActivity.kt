package com.example.kotlinapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        start()
    }

    private fun start() {
        val manager = supportFragmentManager
        manager.beginTransaction().replace(R.id.main_container,
            HomeFragment()
        ).commit()
    }
}
