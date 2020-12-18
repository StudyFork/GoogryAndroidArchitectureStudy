package com.hhi.myapplication.recentSearch

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.hhi.myapplication.R
import com.hhi.myapplication.base.BaseActivity
import com.hhi.myapplication.databinding.ActivityRecentSearchBinding
import com.hhi.myapplication.viewmodel.RecentSearchViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RecentSearchActivity :
    BaseActivity<ActivityRecentSearchBinding>(R.layout.activity_recent_search) {
    private val vm by viewModels<RecentSearchViewModel> {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return RecentSearchViewModel() as T
            }
        }
    }
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

        binding.vm = vm

        setUpUi()

        vm.searchRecentQuery()

    }

    private fun setUpUi() {
        binding.recentSearchRecyclerview.setHasFixedSize(false)
        binding.recentSearchRecyclerview.adapter = adapter
    }
}