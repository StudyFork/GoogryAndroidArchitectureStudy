package com.example.kotlinapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.kotlinapplication.home.HomeFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        start()
    }

    private fun start() {
        onChangeNavMenu("home")
    }

    private fun onChangeNavMenu(menu: String) {
        when (menu) {
            "home" -> {
                val manager = supportFragmentManager
                manager.beginTransaction().replace(R.id.main_container, HomeFragment()).commit()
            }
        }
    }
}
