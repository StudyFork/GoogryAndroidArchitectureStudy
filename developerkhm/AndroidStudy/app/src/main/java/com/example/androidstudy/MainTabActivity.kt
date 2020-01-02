package com.example.androidstudy

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.navigation.ui.AppBarConfiguration.Builder
import androidx.navigation.ui.NavigationUI
import com.example.androidstudy.R.id
import com.example.androidstudy.R.layout
import com.example.androidstudy.database.SearchResultDatabase
import com.example.androidstudy.databinding.ActivityMainTabBinding

class MainTabActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainTabBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this@MainTabActivity, layout.activity_main_tab)

        SearchResultDatabase.getInstance(applicationContext)

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
        NavigationUI.setupWithNavController(binding.navView, navController)
    }

    override fun onDestroy() {
        SearchResultDatabase.destroyInstance()
        super.onDestroy()
    }
}