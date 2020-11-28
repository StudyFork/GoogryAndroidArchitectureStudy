package com.example.androidarchitecturestudy

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.androidarchitecturestudy.databinding.ActivityRecentSearchBinding

class RecentSearchActivity :AppCompatActivity() {

    private lateinit var binding: ActivityRecentSearchBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBindingSet()
    }
    private fun dataBindingSet(){
        binding = DataBindingUtil.setContentView(this,R.layout.activity_recent_search)
        binding.thisActivity = this
    }
}