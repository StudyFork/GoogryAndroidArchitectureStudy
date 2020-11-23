package com.hhi.myapplication.recentSearch

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.hhi.myapplication.R
import com.hhi.myapplication.databinding.ActivityRecentSearchBinding
import kotlinx.android.synthetic.main.activity_recent_search.*

class RecentSearchActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRecentSearchBinding
    private val recyclerAdapter = RecentSearchRecyclerAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_recent_search)
    }

    private fun setUpUI() {
        recent_search_recyclerview.setHasFixedSize(false)
        recent_search_recyclerview.adapter = recyclerAdapter
    }
}