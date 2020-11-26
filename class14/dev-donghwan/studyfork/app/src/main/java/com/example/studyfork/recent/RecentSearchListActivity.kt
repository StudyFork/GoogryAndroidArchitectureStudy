package com.example.studyfork.recent

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.studyfork.R
import com.example.studyfork.base.BaseActivity
import com.example.studyfork.databinding.ActivityRecentSearchListBinding

class RecentSearchListActivity :
    BaseActivity<ActivityRecentSearchListBinding>(R.layout.activity_recent_search_list) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.activity = this

    }
}