package com.example.studyfork.recent

import android.os.Bundle
import com.example.studyfork.R
import com.example.studyfork.base.BaseActivity
import com.example.studyfork.databinding.ActivityRecentSearchListBinding

class RecentSearchListActivity :
    BaseActivity<ActivityRecentSearchListBinding>(R.layout.activity_recent_search_list),
    RecentSearchListContract.View {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.activity = this

    }

    override fun showRecentSearchList(list: List<String>) {
        TODO("Not yet implemented")
    }
}