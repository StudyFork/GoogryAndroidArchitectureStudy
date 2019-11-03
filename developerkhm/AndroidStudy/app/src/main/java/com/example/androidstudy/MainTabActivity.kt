package com.example.androidstudy

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import androidx.navigation.ui.AppBarConfiguration.Builder
import androidx.navigation.ui.NavigationUI
import com.example.androidstudy.R.id
import com.example.androidstudy.R.layout
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainTabActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout.activity_main_tab)
        val navView: BottomNavigationView =
            findViewById(id.nav_view)

        val appBarConfiguration =
            Builder(
                id.navigation_blog,
                id.navigation_news,
                id.navigation_movie,
                id.navigation_book
            )
                .build()
        val navController =
            Navigation.findNavController(
                this,
                id.nav_host_fragment
            )
        NavigationUI.setupActionBarWithNavController(
            this,
            navController,
            appBarConfiguration
        )
        NavigationUI.setupWithNavController(navView, navController)
    }
}