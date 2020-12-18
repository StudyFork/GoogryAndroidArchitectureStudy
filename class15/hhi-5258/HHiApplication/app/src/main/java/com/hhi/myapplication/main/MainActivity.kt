package com.hhi.myapplication.main

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import androidx.activity.viewModels
import androidx.lifecycle.observe
import com.hhi.myapplication.R
import com.hhi.myapplication.base.BaseActivity
import com.hhi.myapplication.databinding.ActivityMainBinding
import com.hhi.myapplication.recentSearch.RecentSearchActivity
import com.hhi.myapplication.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

@AndroidEntryPoint
class MainActivity :
    BaseActivity<ActivityMainBinding>(R.layout.activity_main) {
    private val vm: MainViewModel by viewModels()
    private val adapter = MainRecyclerAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.vm = vm

        setUpUi()
        setObserver()
    }

    private fun setUpUi() {
        binding.mainRecyclerview.setHasFixedSize(false)
        binding.mainRecyclerview.adapter = adapter
    }

    private fun setObserver() {
        vm.hideKeyBoardEvent.observe(this) {
            val inputMethodManager =
                getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(currentFocus?.windowToken, 0)
        }

        vm.emptyQueryEvent.observe(this) {
            showToast("내용을 입력해 주세요")
        }

        vm.searchRecentQueryEvent.observe(this) {
            startActivityForResult(
                Intent(this@MainActivity, RecentSearchActivity::class.java),
                RC_ACTIVITY_FOR_RESULT
            )
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                RC_ACTIVITY_FOR_RESULT -> {
                    val query = data!!.getStringExtra("query")
                    vm.query.value = query
                    vm.searchMovie()
                    binding.mainEditSearch.setText(query)
                }
            }
        }
    }

    companion object {
        private const val RC_ACTIVITY_FOR_RESULT: Int = 100
    }
}
