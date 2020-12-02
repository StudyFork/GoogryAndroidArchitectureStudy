package com.hhi.myapplication.recentSearch

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import com.hhi.myapplication.R
import com.hhi.myapplication.base.BaseActivity
import com.hhi.myapplication.databinding.ActivityRecentSearchBinding
import com.hhi.myapplication.viewmodel.RecentSearchViewModel
import kotlinx.android.synthetic.main.activity_recent_search.*

class RecentSearchActivity :
    BaseActivity<ActivityRecentSearchBinding>(R.layout.activity_recent_search) {
    private val vm = RecentSearchViewModel()
    private val adapter = RecentSearchRecyclerAdapter(
        onClick = { query ->
            val intent = Intent()
            intent.putExtra("query", query)
            setResult(Activity.RESULT_OK, intent)
            finish()
        }
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.activity = this@RecentSearchActivity
        binding.vm = vm

        setUpUi()

        vm.searchRecentQuery()

    }

    private fun setUpUi() {
        binding.recentSearchRecyclerview.setHasFixedSize(false)
        binding.recentSearchRecyclerview.adapter = adapter
    }
}